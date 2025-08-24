package com.paypal.user_service.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt = Instant.now();
    }


}
