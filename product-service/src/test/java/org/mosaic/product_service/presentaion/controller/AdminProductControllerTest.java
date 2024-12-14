package org.mosaic.product_service.presentaion.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mosaic.product_service.application.service.ProductMethodService;
import org.mosaic.product_service.presentaion.dtos.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mosaic.product_service.application.dtos.CreateProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(AdminProductController.class)
@AutoConfigureRestDocs
class AdminProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProductMethodService productMethodService;

	@Autowired
	private ObjectMapper objectMapper;


	@Nested
	@DisplayName("상품 생성")
	class CreateProduct {

		final String user_id = "550e8400-e29b-41d4-a716-446655440006";

		@Test
		@DisplayName("성공 했습니다.")
		void createProductSuccessTest() throws Exception {
			CreateProductRequest request = new CreateProductRequest(
				"f47ac10b-58cc-4372-a567-0e02b2c3c487",
				"f47ac10b-58cc-4372-a567-0e02b1c3d487",
				"샤오미 발 히터",
				"30000",
				"샤오미 발히터입니다.",
				"1000"
			);
			CreateProductResponse response = CreateProductResponse.builder()
				.productUuid("random-uuid")
				.companyId("f47ac10b-58cc-4372-a567-0e02b2c3c487")
				.productHubId("f47ac10b-58cc-4372-a567-0e02b1c3d487")
				.productName("샤오미 발 히터")
				.productPrice(30000L)
				.productDescription("샤오미 발히터입니다.")
				.stockQuantity(1000L)
				.build();

			given(productMethodService.createProduct(any())).willReturn(response);

			mockMvc.perform(post("/api/v1/admin/product")
					.header("X-User-Id", user_id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.success").value(true))
				.andDo(document("create-product",
					requestHeaders(
						headerWithName("X-User-Id").description("사용자 ID")
					),
					requestFields(
						fieldWithPath("companyId").description("회사 ID"),
						fieldWithPath("productHubId").description("상품 허브 ID"),
						fieldWithPath("productName").description("상품 이름"),
						fieldWithPath("productPrice").description("상품 가격"),
						fieldWithPath("productDescription").description("상품 설명"),
						fieldWithPath("stockQuantity").description("재고 수량")
					),
					responseFields(
						fieldWithPath("success").description("요청 성공 여부"),
						fieldWithPath("response.productUuid").description("상품 UUID"),
						fieldWithPath("response.companyId").description("업체 ID"),
						fieldWithPath("response.productHubId").description("상품 허브 ID"),
						fieldWithPath("response.productName").description("상품 이름"),
						fieldWithPath("response.productPrice").description("상품 가격"),
						fieldWithPath("response.productDescription").description("상품설명"),
						fieldWithPath("response.stockQuantity").description("상품 수량")
					)
				));
		}

		@Test
		@DisplayName("실패-가격과 수량이 숫자만 입력 가능합니다.")
		void createProductFailWithNonNumericPriceAndQuantityTest() throws Exception {
			CreateProductRequest request = new CreateProductRequest(
				"f47ac10b-58cc-4372-a567-0e02b2c3c487",
				"f47ac10b-58cc-4372-a567-0e02b1c3d487",
				"샤오미 발 히터",
				"300dfdf00",
				"샤오미 발히터입니다.",
				"100df0"
			);

			mockMvc.perform(post("/api/v1/admin/product")
					.header("X-User-Id", user_id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.stockQuantity").value("숫자만 입력 가능합니다"))
				.andExpect(jsonPath("$.productPrice").value("숫자만 입력 가능합니다"));
		}

	}
}
