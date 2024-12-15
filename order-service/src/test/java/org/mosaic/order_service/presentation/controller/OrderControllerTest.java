package org.mosaic.order_service.presentation.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mosaic.order_service.libs.common.constant.HttpHeaderConstants.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mosaic.order_service.application.dtos.CreateOrderResponse;
import org.mosaic.order_service.application.dtos.OrderDetailResponse;
import org.mosaic.order_service.application.dtos.OrderInfo;
import org.mosaic.order_service.application.service.OrderCommandService;
import org.mosaic.order_service.domain.enums.OrderState;
import org.mosaic.order_service.presentation.dtos.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
@AutoConfigureRestDocs
class OrderControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private OrderCommandService orderCommandService;

  @Nested
  @DisplayName("주문 생성")
  class createOrder {
    final String uri = "/api/v1/orders";
    final String userId = "550e8400-e29b-41d4-a716-446655440006";

    @Test
    @DisplayName("성공-제품넣기")
    void createOrderTest() throws Exception {
      // Given
      CreateOrderRequest request =
          new CreateOrderRequest(
              "550e8400-e29b-41d4-a716-446655440000",
              "550e8400-e29b-41d4-a716-446655440001",
              List.of(
                  new CreateOrderRequest.OrderDetailRequest(
                      "550e8400-e29b-41d4-a716-446655440000", "샤오미 전기 히터", 5L, 100000L),
                  new CreateOrderRequest.OrderDetailRequest(
                      "550e8400-e29b-41d4-a716-446655440002", "토요토미 옴니 230 KS-67H", 1L, 200000L),
                  new CreateOrderRequest.OrderDetailRequest(
                      "550e8400-e29b-41d4-a716-446655440005",
                      "베어샤오슝 BEAR 발난로 DNQ-A02X1",
                      3L,
                      30000L)));

      OrderInfo orderInfo =
          new OrderInfo(
              "abdfc651-401b-4db6-be2b-69f45491d7d7",
              "550e8400-e29b-41d4-a716-446655440000",
              "550e8400-e29b-41d4-a716-446655440001",
              OrderState.PENDING,
              LocalDateTime.parse("2024-12-15T23:55:19.497292"),
              790000L,
              9L);

      List<OrderDetailResponse> orderDetails =
          List.of(
              OrderDetailResponse.builder()
                  .orderDetailUuid("983114b1-9dca-4fd9-a4cb-d6bbe8f474aa")
                  .productId("550e8400-e29b-41d4-a716-446655440000")
                  .productName("샤오미 전기 히터")
                  .quantity(5L)
                  .unitPrice(100000L)
                  .build(),
              OrderDetailResponse.builder()
                  .orderDetailUuid("1a3c363c-bc44-4f99-b3b3-78b74c12cc14")
                  .productId("550e8400-e29b-41d4-a716-446655440002")
                  .productName("토요토미 옴니 230 KS-67H")
                  .quantity(1L)
                  .unitPrice(200000L)
                  .build(),
              OrderDetailResponse.builder()
                  .orderDetailUuid("31d22fd1-96c3-4f0c-b593-5c2cd06c27d2")
                  .productId("550e8400-e29b-41d4-a716-446655440005")
                  .productName("베어샤오슝 BEAR 발난로 DNQ-A02X1")
                  .quantity(3L)
                  .unitPrice(30000L)
                  .build());

      CreateOrderResponse response =
          CreateOrderResponse.builder().orderInfo(orderInfo).orderDetails(orderDetails).build();

      given(orderCommandService.createOrder(any())).willReturn(response);

      // When & Then
      mockMvc
          .perform(
              post(uri)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(new ObjectMapper().writeValueAsString(request))
                  .header(HEADER_USER_ID, userId))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.success").value(true))
          .andExpect(
              jsonPath("$.response.orderInfo.orderUuid")
                  .value("abdfc651-401b-4db6-be2b-69f45491d7d7"))
          .andExpect(jsonPath("$.response.orderDetails", hasSize(3)))
          .andDo(
              document(
                  "create-order",
                  requestHeaders(headerWithName("X-User-Id").description("사용자 ID")),
                  requestFields(
                      fieldWithPath("supplierCompanyId").description("공급업체 ID"),
                      fieldWithPath("receiverCompanyId").description("수신자 회사 ID"),
                      fieldWithPath("orderDetails").description("주문 상세 목록"),
                      fieldWithPath("orderDetails[].productId").description("상품 ID"),
                      fieldWithPath("orderDetails[].productName").description("상품명"),
                      fieldWithPath("orderDetails[].quantity").description("수량"),
                      fieldWithPath("orderDetails[].unitPrice").description("단가")),
                  responseFields(
                      fieldWithPath("success").description("성공 여부"),
                      fieldWithPath("response.orderInfo.orderUuid").description("주문 UUID"),
                      fieldWithPath("response.orderInfo.supplierCompanyId").description("공급업체 ID"),
                      fieldWithPath("response.orderInfo.receiverCompanyId")
                          .description("수신자 회사 ID"),
                      fieldWithPath("response.orderInfo.orderState").description("주문 상태"),
                      fieldWithPath("response.orderInfo.orderDate").description("주문 일시"),
                      fieldWithPath("response.orderInfo.totalAmount").description("총 금액"),
                      fieldWithPath("response.orderInfo.totalQuantity").description("총 수량"),
                      fieldWithPath("response.orderDetails").description("주문 상세 목록"),
                      fieldWithPath("response.orderDetails[].orderDetailUuid")
                          .description("주문 상세 UUID"),
                      fieldWithPath("response.orderDetails[].productId").description("상품 ID"),
                      fieldWithPath("response.orderDetails[].productName").description("상품명"),
                      fieldWithPath("response.orderDetails[].quantity").description("수량"),
                      fieldWithPath("response.orderDetails[].unitPrice").description("단가"))));
    }
  }
}
