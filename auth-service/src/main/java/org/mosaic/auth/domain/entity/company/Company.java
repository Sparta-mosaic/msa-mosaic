package org.mosaic.auth.domain.entity.company;

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
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.user.User;

@Entity
@Table(name = "p_companies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Company extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "company_id")
  private UUID companyId;

  @Column(nullable = false, name = "company_name")
  private String companyName;

  @Column(nullable = false, name = "company_address")
  private String companyAddress;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, name = "company_type")
  private CompanyType companyType;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "hub_id")
  private UUID hubId;

  public void updateCompanyUser(User user) {
    this.user = user;
  }

  public static Company create(UUID companyId, String companyName, String companyAddress, CompanyType companyType, User user, UUID hubId) {
    return Company.builder()
        .companyId(companyId)
        .companyName(companyName)
        .companyAddress(companyAddress)
        .companyType(companyType)
        .user(user)
        .hubId(hubId)
        .build();
  }
}
