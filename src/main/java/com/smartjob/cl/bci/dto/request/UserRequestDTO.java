package com.smartjob.cl.bci.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {


    @Schema(description = "user name", example = "Luis Miguel Naranjo")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, max = 50, message = "Password must be between 8 and 30 characters")
    @JsonProperty("name")
    private String name;

    @Schema(description = "email", example = "lmnz@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    @JsonProperty("email")
    private String email;

    @Schema(description = "password", example = "Colomb&@25")
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @JsonProperty("password")
    private String password;


    @NotEmpty(message = "phones cannot be empty")
    @Valid
    @JsonProperty("phones")
    private List<PhoneRequestDTO> phones;

}
