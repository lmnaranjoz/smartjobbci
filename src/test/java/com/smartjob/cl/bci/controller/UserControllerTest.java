package com.smartjob.cl.bci.controller;

import com.smartjob.cl.bci.common.mapper.UserMapper;
import com.smartjob.cl.bci.dto.request.UserRequestDTO;
import com.smartjob.cl.bci.dto.response.UserResponseDTO;
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerUnitTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private UUID userId;
    private UserModel userModel;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        userModel = UserModel.builder().id(userId).build();
        userResponseDTO = new UserResponseDTO();
    }

    @Test
    void testCreateUser() {
        UserRequestDTO requestDTO = new UserRequestDTO();

        when(userMapper.UserRequestDTOToUserModel(requestDTO)).thenReturn(userModel);
        when(userService.createUser(userModel)).thenReturn(userModel);
        when(userMapper.UserModelToUserResponseDTO(userModel)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.create(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());

        verify(userService).createUser(userModel);
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userModel));
        when(userMapper.UserModelListToUserResponseDTOList(Collections.singletonList(userModel)))
                .thenReturn(Collections.singletonList(userResponseDTO));

        ResponseEntity<?> response = userController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(userResponseDTO), response.getBody());
    }

    @Test
    void testGetByIdFound() {
        when(userService.getUserByID(userId)).thenReturn(Optional.of(userModel));
        when(userMapper.UserModelToUserResponseDTO(userModel)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.getByID(userId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
    }

    @Test
    void testGetByIdNotFound() {
        when(userService.getUserByID(userId)).thenReturn(Optional.empty());
        when(userMapper.UserModelToUserResponseDTO(any(UserModel.class)))
                .thenReturn(new UserResponseDTO());

        ResponseEntity<UserResponseDTO> response = userController.getByID(userId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode()); // según tu código devuelve 200
    }

    @Test
    void testUpdateUser() {
        UserRequestDTO requestDTO = new UserRequestDTO();

        when(userMapper.UserRequestDTOToUserModel(requestDTO)).thenReturn(userModel);
        when(userService.updateUser(userId, userModel)).thenReturn(userModel);
        when(userMapper.UserModelToUserResponseDTO(userModel)).thenReturn(userResponseDTO);

        ResponseEntity<?> response = userController.update(userId.toString(), requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<?> response = userController.delete(userId.toString());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(userId);
    }
}
