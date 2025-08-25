package com.smartjob.cl.bci.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequestDTO implements Serializable {

    @JsonProperty("number")
    @NotEmpty(message = "Number cannot be empty")
    private String number;

    @JsonProperty("citycode")
    @Positive(message = "City code must be greater than zero")
    @NotEmpty(message = "City code cannot be empty")
    private Integer citycode;

    @JsonProperty("contrycode")
    @Positive(message = "Country code must be greater than zero")
    @NotEmpty(message = "Country code cannot be empty")
    private Integer contrycode;
}
