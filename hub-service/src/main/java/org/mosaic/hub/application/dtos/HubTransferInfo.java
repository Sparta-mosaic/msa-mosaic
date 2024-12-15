package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class HubTransferInfo {

    @NotBlank
    private String arrivalHubUuid;

    @PositiveOrZero
    private int estimatedTime;

    @PositiveOrZero
    private double estimatedDistance;
}
