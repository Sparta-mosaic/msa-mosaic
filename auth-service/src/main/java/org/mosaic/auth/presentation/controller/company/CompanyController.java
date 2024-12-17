package org.mosaic.auth.presentation.controller.company;


import static org.mosaic.auth.libs.util.ApiResponseUtils.created;
import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.company.CompanyPageResponse;
import org.mosaic.auth.application.dtos.company.CompanyResponse;
import org.mosaic.auth.application.service.company.CompanyCommandService;
import org.mosaic.auth.application.service.company.CompanyQueryService;
import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.auth.presentation.dtos.company.CreateCompanyRequest;
import org.mosaic.auth.presentation.dtos.company.UpdateCompanyRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<CommonResponse<CompanyResponse>> getCompany(
        @PathVariable String companyUuid) {
        return ok(companyQueryService.findCompanyByUuid(companyUuid));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<CompanyPageResponse>> findPageByQuerydsl(
        @QuerydslPredicate(root = Company.class) Predicate predicate,
        @PageableDefault(sort = "companyId", direction = Sort.Direction.DESC) Pageable pageable) {
        return ok(companyQueryService.findAllByQueryDslPaging(predicate, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MASTER', 'HUB_MANAGER')")
    public ResponseEntity<CommonResponse<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        return created(companyCommandService.createCompany(request.toDTO(), userDetails));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('COMPANY', 'HUB_MANAGER')")
    public ResponseEntity<CommonResponse<CompanyResponse>> updateCompany(
        @RequestBody UpdateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        return ok(companyCommandService.updateCompany(request.toDTO(), userDetails));
    }
}
