package org.mosaic.hub.application.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.hub.domain.model.HubTransfer;

@Getter
@AllArgsConstructor
public class CreateHubTransferResponse {

  private List<HubTransferResponse> hubTransfers;
  private int total;

  public static CreateHubTransferResponse from(
      List<HubTransfer> hubTransfers) {
    return new CreateHubTransferResponse(hubTransfers.stream()
        .map(HubTransferResponse::from)
        .toList(), hubTransfers.size());
  }
}
