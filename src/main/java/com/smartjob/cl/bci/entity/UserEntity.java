package com.smartjob.cl.bci.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name= "password", nullable = false)
    private String password;

    @Column(name = "creation_date")
    private LocalDateTime creatioDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name="token", columnDefinition = "CHAR(36)", nullable = false)
    private String token;

    @Column(name = "is_active")
    private Boolean isActive;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private List<PhoneEntity> phones = new ArrayList<>();

    @PrePersist
    private void generateToken() {
        if (token == null) {
            token = UUID.randomUUID().toString();
        }
    }
}
