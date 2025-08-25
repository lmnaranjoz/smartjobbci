package com.smartjob.cl.bci.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserResponseDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phones")
    private List<PhoneResponseDTO> phones;

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    private LocalDateTime creatioDate;

    @JsonProperty("modified")
    private LocalDateTime updateDate;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isactive")
    private Boolean isActive;
}
