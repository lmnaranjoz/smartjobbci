package com.smartjob.cl.bci.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;
    private UUID id;
    private LocalDateTime creatioDate;
    private LocalDateTime updateDate;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
}
