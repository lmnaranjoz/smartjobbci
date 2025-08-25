package com.smartjob.cl.bci.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "user name", example = "Luis Miguel Naranjo Z")
    @JsonProperty("name")
    private String name;

    @Schema(description = "email", example = "lmnz@gmail.com")
    @JsonProperty("email")
    private String email;

    @JsonProperty("phones")
    private List<PhoneResponseDTO> phones;

    @Schema(description = "id client", example = "2aed95b8-b3f6-42db-b8de-e36407b2e69e")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "creation date", example = "2025-08-25T13:37:16.9872176")
    @JsonProperty("created")
    private LocalDateTime creatioDate;

    @Schema(description = "modification date", example = "2025-08-25T13:40:16.9872176")
    @JsonProperty("modified")
    private LocalDateTime updateDate;

    @Schema(description = "last login date", example = "2025-08-25T13:40:16.9872176")
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @Schema(description = "token", example = "98e74534-ed02-47b5-9cac-f128d869ed52")
    @JsonProperty("token")
    private String token;

    @Schema(description = "state", example = "true")
    @JsonProperty("isactive")
    private Boolean isActive;
}
