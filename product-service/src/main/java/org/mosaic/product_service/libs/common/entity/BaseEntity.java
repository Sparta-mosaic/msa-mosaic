package org.mosaic.product_service.libs.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @CreatedDate
  @Column(name = "CREATED_AT", updatable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(name = "CREATED_BY", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "UPDATED_AT")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private LocalDateTime updatedAt;

  @LastModifiedBy private String updatedBy;

  @Column(name = "DELETED_AT")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private LocalDateTime deletedAt;

  @Column(name = "DELETED_BY")
  private String deletedBy;

  @Column(name = "IS_PUBLIC", nullable = false)
  @ColumnDefault("True")
  protected Boolean isPublic = true;

  @Column(name = "IS_DELETE", nullable = false)
  @ColumnDefault("false")
  protected Boolean isDelete = false;

  public void softDelete(String userUuid) {
    this.isPublic = false;
    this.isDelete = true;
    this.deletedBy = userUuid;
    this.deletedAt = LocalDateTime.now();
  }
}
