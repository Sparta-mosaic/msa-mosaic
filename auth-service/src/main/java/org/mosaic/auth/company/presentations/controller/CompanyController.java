package org.mosaic.auth.company.presentations.controller;

import static org.mosaic.auth.libs.util.ApiResponseUtil.success;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.company.application.dtos.CompanyPageResponse;
import org.mosaic.auth.company.application.dtos.CompanyResponse;
import org.mosaic.auth.company.application.service.CompanyCommandService;
import org.mosaic.auth.company.application.service.CompanyQueryService;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.presentations.dtos.CreateCompanyRequest;
import org.mosaic.auth.company.presentations.dtos.UpdateCompanyRequest;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtil.ApiResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyQueryService companyQueryService;
    private final CompanyCommandService companyCommandService;

    @GetMapping("/{companyUuid}")
    public ResponseEntity<ApiResult<CompanyResponse>> getCompany(
        @PathVariable String companyUuid) {

        return new ResponseEntity<>(success(
            companyQueryService.findCompanyByUuid(companyUuid)),
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResult<CompanyPageResponse>> findPageByQuerydsl(
        @QuerydslPredicate(root = Company.class) Predicate predicate,
        @PageableDefault(sort = "companyId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(success(companyQueryService
            .findAllByQueryDslPaging(predicate, pageable)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){

        return new ResponseEntity<>(success(
            companyCommandService.createCompany(request.toDTO(), userDetails)),
            HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_COMPANY', 'ROLE_HUB_MANAGER')")
    @PutMapping
    public ResponseEntity<ApiResult<CompanyResponse>> updateCompany(
        @RequestBody UpdateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){

        return new ResponseEntity<>(success(
            companyCommandService.updateCompany(request.toDTO(), userDetails)),
            HttpStatus.OK);
    }


}
