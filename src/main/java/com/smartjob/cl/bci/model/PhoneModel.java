package com.smartjob.cl.bci.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneModel {

    private String number;
    private Integer citycode;
    private Integer contrycode;
}
