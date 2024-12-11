package org.mosaic.auth.presentations;

import static org.mosaic.auth.libs.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CompanyResponse;
import org.mosaic.auth.application.service.CompanyService;
import org.mosaic.auth.libs.ApiResponseUtil.ApiResult;
import org.mosaic.auth.presentations.dtos.CreateCompanyRequest;
import org.mosaic.auth.presentations.dtos.UpdateCompanyHubIdRequest;
import org.mosaic.auth.presentations.dtos.UpdateCompanyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResult<CompanyResponse>> getCompany(
        @PathVariable Long companyId) {

        return new ResponseEntity<>(success(
            companyService.findCompanyById(companyId)),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request){

        return new ResponseEntity<>(success(
            companyService.createCompany(request.toDTO())),
            HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResult<CompanyResponse>> updateCompany(
        @RequestBody UpdateCompanyRequest request){

        return new ResponseEntity<>(success(
            companyService.updateCompany(request.toDTO())),
            HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ApiResult<CompanyResponse>> updateCompanyHubId(
        @RequestBody UpdateCompanyHubIdRequest request){

        return new ResponseEntity<>(success(
            companyService.updateCompanyHubId(request.toDTO())),
            HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResult<String>> updateCompanyHubId(
        @PathVariable Long companyId){

        companyService.delete(companyId);

        return new ResponseEntity<>(success("Delete Company Success"),
            HttpStatus.OK);
    }

}
