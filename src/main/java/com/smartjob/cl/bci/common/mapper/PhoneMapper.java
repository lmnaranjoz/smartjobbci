package com.smartjob.cl.bci.common.mapper;

import com.smartjob.cl.bci.entity.PhoneEntity;
import com.smartjob.cl.bci.model.PhoneModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    List<PhoneEntity> PhoneModelListToPhoneEntityList(List<PhoneModel> source);
}
