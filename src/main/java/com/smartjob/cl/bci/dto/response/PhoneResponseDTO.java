package com.smartjob.cl.bci.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDTO {

    @Schema(description = "mobile number", example = "3012398439")
    @JsonProperty("number")
    private String number;

    @Schema(description = "city code", example = "23")
    @JsonProperty("citycode")
    private Integer citycode;

    @Schema(description = "country code", example = "57")
    @JsonProperty("contrycode")
    private Integer contrycode;
}
