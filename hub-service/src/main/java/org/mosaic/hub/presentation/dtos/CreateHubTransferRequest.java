package org.mosaic.hub.presentation.dtos;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.hub.application.dtos.CreateHubTransferServiceRequest;
import org.mosaic.hub.application.dtos.HubTransferInfo;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class CreateHubTransferRequest {

  @NotNull
  @Size(min = 1)
  private List<HubTransferInfo> hubTransferInfoList;

  public CreateHubTransferServiceRequest toService() {
    return CreateHubTransferServiceRequest.create(hubTransferInfoList);
  }
}
