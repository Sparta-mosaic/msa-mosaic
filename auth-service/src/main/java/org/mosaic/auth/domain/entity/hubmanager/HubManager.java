package org.mosaic.auth.domain.entity.hubmanager;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.mosaic.auth.domain.entity.company.BaseEntity;
import org.mosaic.auth.domain.entity.user.User;

@Entity
@Table(name = "p_hub_manager")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HubManager extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "manager_id")
  private Long managerId;

  @UuidGenerator(style = RANDOM)
  @Column(unique = true, nullable = false,
      columnDefinition = "VARCHAR(36)",
      name = "manager_uuid")
  private UUID managerUUID;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  @Column(nullable = false, name = "hub_id")
  private Long hubId;

}
