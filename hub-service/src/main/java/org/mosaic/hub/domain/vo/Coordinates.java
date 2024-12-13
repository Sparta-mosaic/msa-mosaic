package org.mosaic.hub.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.http.HttpStatus;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
public class Coordinates {

  @Column(nullable = false)
  private double latitude;

  @Column(nullable = false)
  private double longitude;

  public Coordinates(double latitude, double longitude) {
    validateCoordinates(latitude, longitude);

    this.latitude = latitude;
    this.longitude = longitude;
  }

  private void validateCoordinates(double latitude, double longitude) {
    if (latitude < 33.0  || latitude > 38.6) {
      throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 위도 정보입니다.");
    }
    if (longitude < 124.0 || longitude > 132.0) {
      throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 경도 정보입니다.");
    }
  }
}
