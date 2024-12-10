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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.user.UserRole;
import org.mosaic.auth.domain.entity.user.Users;
import org.mosaic.auth.libs.auditor.AuditingEntity;

@Entity
@Table(name = "p_companies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Companies extends AuditingEntity {

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

  @Column(nullable = false, name = "slack_email")
  private String slackEmail;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Users user;
}
