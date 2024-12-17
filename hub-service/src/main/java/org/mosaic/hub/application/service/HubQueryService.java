package org.mosaic.hub.application.service;

import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dtos.GetHubPathResponse;
import org.mosaic.hub.application.dtos.HubPageResponse;
import org.mosaic.hub.application.dtos.HubPathResponse;
import org.mosaic.hub.application.dtos.HubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HubQueryService {

  private final HubShortPathService shortPathService;
  private final HubRepository hubRepository;

  @Cacheable(cacheNames = "hubCache", key = "args[0]")
  public HubResponse getHub(String hubUuid) {
    Hub hub = hubRepository.findByUuid(hubUuid).orElseThrow(
        () -> new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 허브입니다."));
    return HubResponse.from(hub);
  }

  public HubPageResponse searchHub(Predicate predicate, Pageable pageable) {
    Page<Hub> hubPage = hubRepository.findAll(predicate, pageable);
    return HubPageResponse.from(hubPage);
  }

  public GetHubPathResponse getHubPath(String departureHubUuid,
      String arrivalHubUuid) {
    Map<String, List<HubPathResponse>> allPaths = shortPathService.findShortestPaths(
        departureHubUuid);
    return GetHubPathResponse.from(
        Optional.ofNullable(allPaths.get(arrivalHubUuid))
            .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST,
                "존재하지 않는 경로입니다.")));
  }
}
