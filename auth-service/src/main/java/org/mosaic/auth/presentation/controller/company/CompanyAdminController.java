package org.mosaic.auth.presentation.controller.company;


import static org.mosaic.auth.libs.util.ApiResponseUtils.created;
import static org.mosaic.auth.libs.util.ApiResponseUtils.noContent;
import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.company.CompanyResponse;
import org.mosaic.auth.application.service.company.CompanyCommandService;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.auth.presentation.dtos.company.CreateCompanyRequest;
import org.mosaic.auth.presentation.dtos.company.UpdateCompanyHubIdRequest;
import org.mosaic.auth.presentation.dtos.company.UpdateCompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admin/company")
@RequiredArgsConstructor
public class CompanyAdminController {

    private final CompanyCommandService companyCommandService;

    @PostMapping
    public ResponseEntity<CommonResponse<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        return created(companyCommandService
            .createCompany(request.toDTO(), userDetails));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('MASTER', 'HUB_MANAGER', 'COMPANY')")
    public ResponseEntity<CommonResponse<CompanyResponse>> updateCompany(
        @RequestBody UpdateCompanyRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        return ok(companyCommandService
            .updateCompany(request.toDTO(), userDetails));
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_HUB_MANAGER', 'ROLE_COMPANY')")
    public ResponseEntity<CommonResponse<CompanyResponse>> updateCompanyHubId(
        @RequestBody UpdateCompanyHubIdRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        return ok(companyCommandService
            .updateCompanyHubId(request.toDTO(), userDetails));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CommonResponse<Void>> deleteCompany(
        @PathVariable String companyId,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        companyCommandService.delete(companyId, userDetails);
        return noContent();
    }
}
