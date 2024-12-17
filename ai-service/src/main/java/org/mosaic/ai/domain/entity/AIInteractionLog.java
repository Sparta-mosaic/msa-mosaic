package org.mosaic.ai.domain.entity;

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
import org.mosaic.ai.libs.entity.BaseEntity;

@Entity
@Table(name = "p_ai_prompt_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
public class AIInteractionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_id")
    private Long aiId;

    @UuidGenerator(style = RANDOM)
    @Column(unique = true, nullable = false,
        columnDefinition = "VARCHAR(36)",
        name = "ai_uuid")
    private String aiUuid;

    @Column(name = "user_prompt", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String userPrompt;

    @Column(name = "ai_response", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String aiResponse;

    @Column(name = "ai_model_name", nullable = false)
    private String aiModelName;

    public static AIInteractionLog create(String userPrompt, String aiResponse, String aiModelName) {
        return AIInteractionLog.builder()
            .userPrompt(userPrompt)
            .aiResponse(aiResponse)
            .aiModelName(aiModelName)
            .build();
    }

}
