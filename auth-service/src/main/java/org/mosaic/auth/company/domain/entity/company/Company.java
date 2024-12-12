package org.mosaic.auth.company.domain.entity.company;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.mosaic.auth.config.base.BaseEntity;
import org.mosaic.auth.user.domain.entity.user.User;

@Entity
@Table(name = "p_companies",
    indexes = @Index(name = "idx_company_id", columnList = "company_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Company extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_id")
  private Long companyId;

  @UuidGenerator(style = RANDOM)
  @Column(unique = true, nullable = false,
      columnDefinition = "VARCHAR(36)",
      name = "company_uuid")
  private String companyUUID;

  @Column(nullable = false, name = "company_name")
  private String companyName;

  @Column(nullable = false, name = "company_address")
  private String companyAddress;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, name = "company_type")
  private CompanyType companyType;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  @Column(nullable = false, name = "hub_id")
  private Long hubId;

  public static Company create(Long companyId, String companyName, String companyAddress, CompanyType companyType, User user, Long hubId) {
    return Company.builder()
        .companyId(companyId)
        .companyName(companyName)
        .companyAddress(companyAddress)
        .companyType(companyType)
        .user(user)
        .hubId(hubId)
        .build();
  }

  public void update(String name, String address, CompanyType companyType) {
    this.companyName = name;
    this.companyAddress = address;
    this.companyType = companyType;
  }

  public void updateHubId(Long hubId) {
    this.hubId = hubId;
  }
}
