package org.mosaic.slack.application.dto;

import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackChannelTemplate {

  public static List<LayoutBlock> of() {
    return Arrays.asList(
        SectionBlock.builder()
            .text(MarkdownTextObject.builder()
                .text("새로운 배송 스케줄이 전달되었습니다! 🚛🚛🚛")
                .build())
            .build()
    );
  }

}
