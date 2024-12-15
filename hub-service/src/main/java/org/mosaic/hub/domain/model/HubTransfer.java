package org.mosaic.hub.domain.model;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.mosaic.hub.libs.entity.BaseEntity;

@Getter
@Entity
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@SQLRestriction("is_deleted = false")
@Table(name = "p_hub_transfers")
public class HubTransfer extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hub_transfer_id")
  private Long id;

  @Column(nullable = false, name = "hub_transfer_uuid", unique = true)
  private String uuid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departure_hub_id")
  private Hub departureHub;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "arrival_hub_id")
  private Hub arrivalHub;

  @Column(nullable = false)
  private int estimatedTime;

  @Column(nullable = false)
  private double estimatedDistance;

  public static HubTransfer create(
      Hub departureHub, Hub arrivalHub,
      int estimatedTime, double estimatedDistance) {
    return HubTransfer.builder()
        .uuid(UUID.randomUUID().toString())
        .departureHub(departureHub)
        .arrivalHub(arrivalHub)
        .estimatedTime(estimatedTime)
        .estimatedDistance(estimatedDistance)
        .build();
  }

  public void update(
      int estimatedTime, double estimatedDistance) {
    this.estimatedTime = estimatedTime;
    this.estimatedDistance = estimatedDistance;
  }
}
