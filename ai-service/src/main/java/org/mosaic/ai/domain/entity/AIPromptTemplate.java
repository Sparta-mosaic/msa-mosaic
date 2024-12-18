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
@Table(name = "p_ai_prompt_templates")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
public class AIPromptTemplate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateId;

    @UuidGenerator(style = RANDOM)
    @Column(unique = true, nullable = false,
        columnDefinition = "VARCHAR(36)",
        name = "template_uuid")
    private String templateUuid;

    @Column(name = "user_prompt", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String template;

    public static AIPromptTemplate create(String template) {
        return AIPromptTemplate.builder()
            .template(template)
            .build();
    }

}
