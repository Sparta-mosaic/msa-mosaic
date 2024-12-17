package org.mosaic.auth.domain.model.delivery_driver;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.mosaic.auth.libs.config.base.BaseEntity;

@Getter
@Entity
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@SQLRestriction("is_deleted = false")
@Table(name = "p_delivery_drivers")
public class DeliveryDriver extends BaseEntity {

  @Id
  @Column(name = "delivery_driver_id")
  private Long id;

  @Column(nullable = false, name = "delivery_driver_uuid", unique = true)
  private String uuid;

  private String hubUuid;

  @Column(nullable = false, name = "slack_email")
  private String slackEmail;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DeliveryDriverType type;

  @Column(nullable = false)
  private int deliveryOrder;

  @Column
  private Boolean isLatest;

  public static DeliveryDriver create(
      Long userId, String hubUuid, String slackEmail,
      DeliveryDriverType type, int deliveryOrder) {
    return DeliveryDriver.builder()
        .id(userId)
        .uuid(UUID.randomUUID().toString())
        .hubUuid(hubUuid)
        .slackEmail(slackEmail)
        .type(type)
        .deliveryOrder(deliveryOrder)
        .build();
  }
}
