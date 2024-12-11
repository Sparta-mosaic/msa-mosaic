package org.mosaic.auth.domain.entity.user;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.mosaic.auth.domain.entity.company.BaseEntity;

@Entity
@Table(name = "p_users"
    , indexes = @Index(name = "idx_user_id", columnList = "user_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @UuidGenerator(style = RANDOM)
  @Column(unique = true, nullable = false,
      columnDefinition = "VARCHAR(36)",
      name = "user_uuid")
  private UUID userUUID;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;

  @Column(nullable = false, name = "slack_email")
  private String slackEmail;

  public static User create(String username, String password, UserRole role,
      String slackEmail) {
    return User.builder()
        .username(username)
        .password(password)
        .role(role)
        .slackEmail(slackEmail)
        .build();
  }
}
