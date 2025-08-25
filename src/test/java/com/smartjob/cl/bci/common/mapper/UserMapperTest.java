package com.smartjob.cl.bci.common.mapper;

import com.smartjob.cl.bci.dto.request.UserRequestDTO;
import com.smartjob.cl.bci.dto.response.UserResponseDTO;
import com.smartjob.cl.bci.entity.UserEntity;
import com.smartjob.cl.bci.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        // Obtiene la implementación generada por MapStruct
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void testUserRequestDTOToUserModel() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Juan");
        requestDTO.setEmail("juan@mail.com");
        requestDTO.setPassword("Password123");

        UserModel userModel = userMapper.UserRequestDTOToUserModel(requestDTO);

        assertNotNull(userModel);
        assertEquals(requestDTO.getName(), userModel.getName());
        assertEquals(requestDTO.getEmail(), userModel.getEmail());
        assertEquals(requestDTO.getPassword(), userModel.getPassword());
    }

    @Test
    void testUserRequestDTOListToUserModelList() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Juan");
        requestDTO.setEmail("juan@mail.com");
        requestDTO.setPassword("Password123");

        List<UserModel> result = userMapper.UserRequestDTOListToUserModelList(Collections.singletonList(requestDTO));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getName());
    }

    @Test
    void testUserModelToUserResponseDTO() {
        UserModel userModel = UserModel.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .password("Password123")
                .build();

        UserResponseDTO responseDTO = userMapper.UserModelToUserResponseDTO(userModel);

        assertNotNull(responseDTO);
        assertEquals(userModel.getName(), responseDTO.getName());
        assertEquals(userModel.getEmail(), responseDTO.getEmail());
    }

    @Test
    void testUserModelListToUserResponseDTOList() {
        UserModel userModel = UserModel.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .password("Password123")
                .build();

        List<UserResponseDTO> result = userMapper.UserModelListToUserResponseDTOList(Collections.singletonList(userModel));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getName());
    }

    @Test
    void testUserModelToUserEntityAndBack() {
        UserModel userModel = UserModel.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .password("Password123")
                .build();

        UserEntity userEntity = userMapper.UserModelToUserEntity(userModel);
        assertNotNull(userEntity);
        assertEquals(userModel.getName(), userEntity.getName());

        UserModel mappedBack = userMapper.UserEntityToUserModel(userEntity);
        assertNotNull(mappedBack);
        assertEquals(userModel.getName(), mappedBack.getName());
    }

    @Test
    void testUserEntityListToUserModelList() {
        UserModel userModel = UserModel.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .password("Password123")
                .build();

        UserEntity userEntity = userMapper.UserModelToUserEntity(userModel);

        List<UserModel> result = userMapper.UserEntityListToUserModelList(Collections.singletonList(userEntity));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userEntity.getName(), result.get(0).getName());
    }
}

