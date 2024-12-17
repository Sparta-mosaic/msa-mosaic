package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class CreateHubTransferServiceRequest {

  private List<HubTransferInfo> hubTransferInfoList;

  public static CreateHubTransferServiceRequest create(
      List<HubTransferInfo> hubTransferInfoList) {
    return new CreateHubTransferServiceRequest(hubTransferInfoList);
  }
}
