package com.easybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_at",insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        if(this.createdAt == null) this.createdAt = LocalDateTime.now();
        if(!StringUtils.hasLength(this.createdBy))this.createdBy = "system"; // Replace with actual user
    }

    @PostUpdate
    public void postUpdate() {
        if(this.updatedAt == null) this.updatedAt = LocalDateTime.now();
        if(!StringUtils.hasLength(this.updatedBy)) this.updatedBy = "system"; // Replace with actual user
    }
}
