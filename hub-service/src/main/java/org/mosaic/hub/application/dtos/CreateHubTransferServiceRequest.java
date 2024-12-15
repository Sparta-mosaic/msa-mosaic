package org.mosaic.hub.application.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateHubTransferServiceRequest {

  private List<HubTransferInfo> hubTransferInfoList;

  public static CreateHubTransferServiceRequest create(
      List<HubTransferInfo> hubTransferInfoList) {
    return new CreateHubTransferServiceRequest(hubTransferInfoList);
  }
}
