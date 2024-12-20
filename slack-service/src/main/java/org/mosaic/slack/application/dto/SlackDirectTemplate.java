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
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackDirectTemplate {

  public static List<LayoutBlock> of(SlackMessageDto dto) {
    return Arrays.asList(
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("*새로운 배송 스케줄 알림이 왔어요! 🚛*")
                .build())
            .build(),
        new DividerBlock(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text(
                    "*주문 번호:* " + dto.getOrderId() +
                        "\n*주문자 정보:* " + dto.getCustomerName() +
                        "/" + dto.getCustomerEmail() +
                        "\n*상품 정보:* " + dto.getProductInfo()
                )
                .build())
            .build(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text(
                    "*발송지:*\n" + dto.getDeparture() +
                        "\n*도착지:*\n" + dto.getArrival() +
                        "\n*배송담당자:*\n" + dto.getDeliveryPersonName()+
                        "/" + dto.getDeliveryPersonEmail()  +
                        "\n*배송 요청 기한:* " + dto.getDeliveryDeadline()
                )
                .build())
            .build(),
        new DividerBlock(),
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text(
                   dto.getMessage()
                )
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
