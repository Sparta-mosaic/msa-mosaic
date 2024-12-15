package org.mosaic.slack.application.dto;

import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.ButtonElement;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SlackMessageBuilderDto {

  private String orderNo;
  private String customerInfo;
  private String productInfo;
  private String departure;
  private String stopOver;
  private String arrival;
  private String deliveryPersonInfo;
  private String orderText;
  private String deliveryDeadline;

  public static SlackMessageBuilderDto create(
      String orderNo, String customerInfo, String productInfo,
      String departure, String stopOver, String arrival,
      String deliveryPersonInfo, String orderText, String deliveryDeadline) {

    return SlackMessageBuilderDto.builder()
        .orderNo(orderNo)
        .customerInfo(customerInfo)
        .productInfo(productInfo)
        .departure(departure)
        .stopOver(stopOver)
        .arrival(arrival)
        .deliveryPersonInfo(deliveryPersonInfo)
        .orderText(orderText)
        .deliveryDeadline(deliveryDeadline)
        .build();
  }

  public static List<LayoutBlock> buildMessage(SlackMessageBuilderDto dto) {
    return Arrays.asList(
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("*새로운 배송 스케쥴 알림이 왔어요! 🚛*")
                .build())
            .build(),
        new DividerBlock(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("*주문 번호:* " + dto.getOrderNo() + "\n*주문자 정보:* " + dto.getCustomerInfo() + "\n*상품 정보:* " + dto.getProductInfo())
                .build())
            .build(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("*발송지:*\n" + dto.getDeparture() + "\n*경유지:*\n" + dto.getStopOver() + "\n*도착지:*\n" + dto.getArrival() +
                    "\n*배송담당자:*\n" + dto.getDeliveryPersonInfo() + "\n*배송 요청 사항:* " + dto.getOrderText())
                .build())
            .build(),
        new DividerBlock(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("위 내용을 기반으로 도출된 최종 발송 시한은 *" + dto.getDeliveryDeadline() + "* 입니다.")
                .build())
            .build(),
        ActionsBlock.builder()
            .elements(Arrays.asList(
                ButtonElement.builder()
                    .text(PlainTextObject.builder().text("Show Details").emoji(true).build())
                    .style("primary")
                    .value("click_me_123")
                    .build(),
                ButtonElement.builder()
                    .text(PlainTextObject.builder().text("Deny").emoji(true).build())
                    .style("danger")
                    .value("click_me_123")
                    .build()
            ))
            .build()
    );
  }

}
