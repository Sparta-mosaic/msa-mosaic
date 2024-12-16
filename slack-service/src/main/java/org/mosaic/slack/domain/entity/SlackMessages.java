package org.mosaic.slack.domain.entity;

import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;
import org.mosaic.slack.libs.config.base.BaseEntity;

@Entity
@Table(name = "p_slack_messages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
public class SlackMessages extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "slack_message_id")
  private Long slackMessageId;

  @UuidGenerator(style = RANDOM)
  @Column(unique = true, nullable = false,
      columnDefinition = "VARCHAR(36)",
      name = "slack_message_uuid")
  private String slackMessageUUID;

  @Column(nullable = false, name = "receiver_slack_id")
  private String receiverSlackId;

  @Column(nullable = false, name = "receiver_slack_email")
  private String receiverSlackEmail;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String text;


  public static SlackMessages create(String slackId,String email, String text) {
    return SlackMessages.builder()
        .receiverSlackId(slackId)
        .receiverSlackEmail(email)
        .text(text)
        .build();
  }
}
