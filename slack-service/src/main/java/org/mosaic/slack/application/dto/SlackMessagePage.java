package org.mosaic.slack.application.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class SlackMessagePage extends PagedModel<SlackMessageResponse> {

  public SlackMessagePage(
      Page<SlackMessageResponse> page) {
    super(
        new PageImpl<>(
            page.getContent(),
            page.getPageable(),
            page.getTotalElements()
        )
    );
  }
}
