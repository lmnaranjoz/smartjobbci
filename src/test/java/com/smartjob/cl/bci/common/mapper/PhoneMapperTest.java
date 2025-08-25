package com.smartjob.cl.bci.common.mapper;

import com.smartjob.cl.bci.entity.PhoneEntity;
import com.smartjob.cl.bci.model.PhoneModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneMapperTest {

    private PhoneMapper phoneMapper;

    @BeforeEach
    void setUp() {
        // Obtiene la implementación generada por MapStruct
        phoneMapper = Mappers.getMapper(PhoneMapper.class);
    }

    @Test
    void testPhoneModelListToPhoneEntityList() {
        PhoneModel phone1 = PhoneModel.builder()
                .number("1234")
                .citycode(1)
                .contrycode(57)
                .build();

        PhoneModel phone2 = PhoneModel.builder()
                .number("5678")
                .citycode(2)
                .contrycode(57)
                .build();

        List<PhoneModel> phoneModels = Arrays.asList(phone1, phone2);

        List<PhoneEntity> phoneEntities = phoneMapper.PhoneModelListToPhoneEntityList(phoneModels);

        assertNotNull(phoneEntities);
        assertEquals(phoneModels.size(), phoneEntities.size());
        assertEquals(phone1.getNumber(), phoneEntities.get(0).getNumber());
        assertEquals(phone2.getCitycode(), phoneEntities.get(1).getCitycode());
        assertEquals(phone2.getContrycode(), phoneEntities.get(1).getContrycode());
    }

    @Test
    void testEmptyListReturnsEmptyList() {
        List<PhoneEntity> result = phoneMapper.PhoneModelListToPhoneEntityList(List.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNullListReturnsNull() {
        List<PhoneEntity> result = phoneMapper.PhoneModelListToPhoneEntityList(null);
        assertNull(result);
    }
}
