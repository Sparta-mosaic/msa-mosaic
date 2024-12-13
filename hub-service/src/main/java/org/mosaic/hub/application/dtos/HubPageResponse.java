package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.hub.domain.model.Hub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@AllArgsConstructor
public class HubPageResponse {

  private HubPage hubPage;

  public static HubPageResponse from(Page<Hub> hubPage) {
    return new HubPageResponse(new HubPage(hubPage));
  }

  private static class HubPage extends PagedModel<HubPage.HubInfo> {

    private HubPage(Page<Hub> hubPage) {
      super(new PageImpl<>(
          HubInfo.from(hubPage.getContent()),
          hubPage.getPageable(),
          hubPage.getTotalElements()));
    }

    @Getter
    @Builder(access = PRIVATE)
    @AllArgsConstructor
    private static class HubInfo {

      private String hubUuid;
      private Long managerId;
      private String name;
      private String address;
      private double latitude;
      private double longitude;
      private LocalDateTime createdAt;
      private String createdBy;
      private LocalDateTime updatedAt;
      private String updatedBy;
      private boolean isPublic;

      private static List<HubInfo> from(List<Hub> hubs) {
        return hubs.stream()
            .map(HubInfo::from)
            .toList();
      }

      private static HubInfo from(Hub hub) {
        return HubInfo.builder()
            .hubUuid(hub.getUuid())
            .managerId(hub.getManagerId())
            .name(hub.getName())
            .address(hub.getAddress())
            .latitude(hub.getCoordinates().getLatitude())
            .longitude(hub.getCoordinates().getLongitude())
            .createdAt(hub.getCreatedAt())
            .createdBy(hub.getCreatedBy())
            .updatedAt(hub.getUpdatedAt())
            .updatedBy(hub.getUpdatedBy())
            .isPublic(hub.isPublic())
            .build();
      }
    }
  }
}
