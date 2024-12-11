package org.mosaic.auth.presentations;

import static org.mosaic.auth.libs.ApiResponseUtil.success;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CompanyResponse;
import org.mosaic.auth.application.service.CompanyService;
import org.mosaic.auth.libs.ApiResponseUtil.ApiResult;
import org.mosaic.auth.presentations.dtos.CreateCompanyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        @PathVariable String companyId) {

        return new ResponseEntity<>(success(
            companyService.findCompanyById(UUID.fromString(companyId))),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request){

        return new ResponseEntity<>(success(
            companyService.createCompany(request.toDTO())),
            HttpStatus.CREATED);
    }

}
