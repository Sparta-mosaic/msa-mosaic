package org.mosaic.auth.domain.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.company.AuditingEntity;

@Entity
@Table(name = "p_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private UUID userId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;

  @Column(nullable = false, name = "slack_email")
  private String slackEmail;

  public static User create(String username, String password, UserRole role, String slackEmail) {
    return User.builder()
        .username(username)
        .password(password)
        .role(role)
        .slackEmail(slackEmail)
        .build();
  }
}
