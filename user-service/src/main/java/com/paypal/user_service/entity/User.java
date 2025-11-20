package com.paypal.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "app_user")
public class User extends BaseEntity{
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
}
