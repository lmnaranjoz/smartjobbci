package com.smartjob.cl.bci.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "mobile number", example = "3012398439")
    @NotEmpty(message = "Number cannot be empty")
    @Pattern(regexp = "^\\d+$", message = "Invalid number")
    @JsonProperty("number")
    private String number;

    @Schema(description = "city code", example = "23")
    @Positive(message = "City code must be greater than zero")
    @NotNull(message = "City code cannot be null")
    @JsonProperty("citycode")
    private Integer citycode;

    @Schema(description = "country code", example = "57")
    @Positive(message = "Country code must be greater than zero")
    @NotNull(message = "Country code cannot be null")
    @JsonProperty("contrycode")
    private Integer contrycode;
}
