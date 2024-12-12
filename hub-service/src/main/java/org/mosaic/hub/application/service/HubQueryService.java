package org.mosaic.hub.application.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.application.dto.HubPageResponse;
import org.mosaic.hub.application.dto.HubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HubQueryService {

  private final HubRepository hubRepository;

  public HubResponse getHub(String hubUuid) {
    Hub hub = hubRepository.findByUuid(hubUuid).orElseThrow(
        () -> new CustomException(HttpStatus.NOT_FOUND, "존재하지 않는 허브입니다."));
    return HubResponse.from(hub);
  }

  public HubPageResponse searchHub(Predicate predicate, Pageable pageable) {
    Page<Hub> hubPage = hubRepository.findAll(predicate, pageable);
    return HubPageResponse.from(hubPage);
  }
}
