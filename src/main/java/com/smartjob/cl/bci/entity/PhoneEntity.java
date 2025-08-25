package com.smartjob.cl.bci.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name="phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "citycode", nullable = false)
    private Integer citycode;

    @Column(name = "contrycode", nullable = false)
    private Integer contrycode;


    @ManyToOne
    @JoinColumn(name = "user_id")
    //@JsonBackReference
    private UserEntity user;


}
