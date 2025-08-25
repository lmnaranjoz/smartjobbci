package com.smartjob.cl.bci.service;

import com.smartjob.cl.bci.common.exception.EmailException;
import com.smartjob.cl.bci.common.exception.PasswordException;
import com.smartjob.cl.bci.common.exception.UserNotFoundException;
import com.smartjob.cl.bci.common.mapper.PhoneMapper;
import com.smartjob.cl.bci.common.mapper.UserMapper;
import com.smartjob.cl.bci.common.validator.EmailValidator;
import com.smartjob.cl.bci.common.validator.PasswordValidator;
import com.smartjob.cl.bci.entity.UserEntity;
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;



public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PhoneMapper phoneMapper;

    @Mock
    private PasswordValidator passwordValidator;

    @Mock
    private EmailValidator emailValidator;

    @InjectMocks
    private UserService userService;

    private UserModel userModel;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userModel = new UserModel();
        userModel.setName("Test User");
        userModel.setEmail("test@test.com");
        userModel.setPassword("Abc123!");

        userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setEmail("test@test.com");
        userEntity.setPassword("Abc123!");
    }

    @Test
    void createUser_success() {
        when(passwordValidator.isValidPassword(anyString())).thenReturn(true);
        when(emailValidator.isValidEmail(anyString())).thenReturn(true);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userMapper.UserModelToUserEntity(any(UserModel.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.UserEntityToUserModel(any(UserEntity.class))).thenReturn(userModel);

        UserModel result = userService.createUser(userModel);

        assertNotNull(result);
        assertEquals("test@test.com", result.getEmail());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void createUser_invalidPassword_throwsException() {
        when(passwordValidator.isValidPassword(anyString())).thenReturn(false);

        assertThrows(PasswordException.class, () -> userService.createUser(userModel));
    }

    @Test
    void createUser_invalidEmail_throwsException() {
        when(passwordValidator.isValidPassword(anyString())).thenReturn(true);
        when(emailValidator.isValidEmail(anyString())).thenReturn(false);

        assertThrows(EmailException.class, () -> userService.createUser(userModel));
    }

    @Test
    void getUserById_notFound_throwsException() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByID(id));
    }

    @Test
    void deleteUser_success() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        userService.deleteUser(id);

        verify(userRepository, times(1)).delete(userEntity);
    }
}
