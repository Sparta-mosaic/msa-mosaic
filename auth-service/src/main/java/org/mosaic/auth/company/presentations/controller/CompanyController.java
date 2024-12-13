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
import org.mosaic.auth.libs.util.ApiResponseUtil.ApiResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResult<CompanyResponse>> getCompany(
        @PathVariable Long companyId) {

        return new ResponseEntity<>(success(
            companyQueryService.findCompanyById(companyId)),
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CompanyPageResponse> findPageByQuerydsl(
        @QuerydslPredicate(root = Company.class) Predicate predicate,
        @PageableDefault(sort = "companyId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(companyQueryService
            .findAllByQueryDslPaging(predicate, pageable));
    }

    /*
        HUB_MANAGER:  담당 허브의 업체 정보만 생성 가능
     */
    @PostMapping
    public ResponseEntity<ApiResult<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request){

        return new ResponseEntity<>(success(
            companyCommandService.createCompany(request.toDTO())),
            HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResult<CompanyResponse>> updateCompany(
        @RequestBody UpdateCompanyRequest request){

        return new ResponseEntity<>(success(
            companyCommandService.updateCompany(request.toDTO())),
            HttpStatus.OK);
    }

    /*
        HUB_MANAGER:  담당 허브의 업체 정보만 삭제 가능
     */
    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResult<String>> deleteCompanyByManager(
        @PathVariable Long companyId){

        companyCommandService.delete(companyId);

        return new ResponseEntity<>(success("Delete Company Success"),
            HttpStatus.OK);
    }

}
