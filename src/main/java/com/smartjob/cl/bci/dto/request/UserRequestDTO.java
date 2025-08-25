package com.smartjob.cl.bci.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("name")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, max = 50, message = "Password must be between 8 and 30 characters")
    private String name;

    @JsonProperty("email")
    @NotEmpty(message = "Name cannot be empty")
    @Email(message = "Email must be valid")
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    @JsonProperty("phones")
    @NotEmpty(message = "phones cannot be empty")
    private List<PhoneRequestDTO> phones;

}
