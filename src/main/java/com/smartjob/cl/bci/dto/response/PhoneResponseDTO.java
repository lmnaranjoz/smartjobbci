package com.smartjob.cl.bci.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDTO {

    @JsonProperty("number")
    private String number;

    @JsonProperty("citycode")
    private Integer citycode;

    @JsonProperty("contrycode")
    private Integer contrycode;
}
