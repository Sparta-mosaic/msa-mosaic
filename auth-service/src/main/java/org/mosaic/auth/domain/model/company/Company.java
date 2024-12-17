package org.mosaic.auth.domain.model.company;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;
import org.mosaic.auth.libs.config.base.BaseEntity;
import org.mosaic.auth.domain.model.user.User;

@Entity
@Table(name = "p_companies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
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

  @Column(nullable = false, name = "hub_uuid")
  private String hubUuid;

  public static Company create(Long companyId, String companyName, String companyAddress, CompanyType companyType, User user, String hubUuid) {
    return Company.builder()
        .companyId(companyId)
        .companyName(companyName)
        .companyAddress(companyAddress)
        .companyType(companyType)
        .user(user)
        .hubUuid(hubUuid)
        .build();
  }

  public void update(String name, String address, CompanyType companyType) {
    this.companyName = name;
    this.companyAddress = address;
    this.companyType = companyType;
  }

  public void updateHubId(String hubUuid) {
    this.hubUuid = hubUuid;
  }
}
