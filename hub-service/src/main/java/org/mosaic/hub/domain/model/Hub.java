package org.mosaic.hub.domain.model;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.mosaic.hub.domain.vo.Coordinates;
import org.mosaic.hub.libs.entity.BaseEntity;

@Getter
@Entity
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@SQLRestriction("is_deleted = false")
@Table(name = "p_hubs", indexes = @Index(name = "idx_uuid", columnList = "hub_uuid"))
public class Hub extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hub_id")
  private Long id;

  @Column(nullable = false, name = "hub_uuid", unique = true)
  private String uuid;

  @Column
  private Long managerId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Embedded
  private Coordinates coordinates;

  public static Hub createHub(
      Long managerId, String name, String address,
      double latitude, double longitude) {
    return Hub.builder()
        .uuid(UUID.randomUUID().toString())
        .managerId(managerId)
        .name(name)
        .address(address)
        .coordinates(new Coordinates(latitude, longitude))
        .build();
  }

  public void update(
      Long managerId, String name, String address,
      double latitude, double longitude) {
    this.managerId = managerId;
    this.name = name;
    this.address = address;
    this.coordinates = new Coordinates(latitude, longitude);
  }
}