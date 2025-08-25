package com.smartjob.cl.bci.service;

import com.smartjob.cl.bci.common.exception.EmailException;
import com.smartjob.cl.bci.common.exception.PasswordException;
import com.smartjob.cl.bci.common.exception.UserNotFoundException;
import com.smartjob.cl.bci.common.mapper.PhoneMapper;
import com.smartjob.cl.bci.common.mapper.UserMapper;
import com.smartjob.cl.bci.common.validator.EmailValidator;
import com.smartjob.cl.bci.common.validator.PasswordValidator;
import com.smartjob.cl.bci.entity.PhoneEntity;
import com.smartjob.cl.bci.entity.UserEntity;
import com.smartjob.cl.bci.model.PhoneModel;
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

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

        userModel = UserModel.builder()
                .id(UUID.randomUUID())
                .name("Juan")
                .email("juan@mail.com")
                .password("Password123")
                .phones(Collections.singletonList(PhoneModel.builder().number("1234").citycode(1).contrycode(57).build()))
                .build();

        userEntity = UserEntity.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .phones(new ArrayList<>())
                .build();
    }

    @Test
    void testCreateUser_Success() {
        when(passwordValidator.isValidPassword(userModel.getPassword())).thenReturn(true);
        when(emailValidator.isValidEmail(userModel.getEmail())).thenReturn(true);
        when(userRepository.existsByEmail(userModel.getEmail())).thenReturn(false);
        when(userMapper.UserModelToUserEntity(userModel)).thenReturn(userEntity);
        when(phoneMapper.PhoneModelListToPhoneEntityList(userModel.getPhones()))
                .thenReturn(Collections.singletonList(new PhoneEntity()));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.UserEntityToUserModel(userEntity)).thenReturn(userModel);

        UserModel result = userService.createUser(userModel);

        assertNotNull(result);
        assertEquals(userModel.getEmail(), result.getEmail());
        verify(userRepository).save(userEntity);
    }

    @Test
    void testCreateUser_InvalidPassword_ThrowsException() {
        when(passwordValidator.isValidPassword(userModel.getPassword())).thenReturn(false);

        assertThrows(PasswordException.class, () -> userService.createUser(userModel));
    }

    @Test
    void testCreateUser_InvalidEmail_ThrowsException() {
        when(passwordValidator.isValidPassword(userModel.getPassword())).thenReturn(true);
        when(emailValidator.isValidEmail(userModel.getEmail())).thenReturn(false);

        assertThrows(EmailException.class, () -> userService.createUser(userModel));
    }

    @Test
    void testCreateUser_EmailAlreadyExists_ThrowsException() {
        when(passwordValidator.isValidPassword(userModel.getPassword())).thenReturn(true);
        when(emailValidator.isValidEmail(userModel.getEmail())).thenReturn(true);
        when(userRepository.existsByEmail(userModel.getEmail())).thenReturn(true);

        assertThrows(EmailException.class, () -> userService.createUser(userModel));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));
        when(userMapper.UserEntityListToUserModelList(Collections.singletonList(userEntity)))
                .thenReturn(Collections.singletonList(userModel));

        List<UserModel> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserByID_Found() {
        UUID id = userModel.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        when(userMapper.UserEntityToUserModel(userEntity)).thenReturn(userModel);

        Optional<UserModel> result = userService.getUserByID(id);

        assertTrue(result.isPresent());
        assertEquals(userModel.getEmail(), result.get().getEmail());
    }

    @Test
    void testGetUserByID_NotFound() {
        UUID id = userModel.getId();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByID(id));
    }

    @Test
    void testDeleteUser_Success() {
        UUID id = userModel.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        doNothing().when(userRepository).delete(userEntity);

        assertDoesNotThrow(() -> userService.deleteUser(id));
        verify(userRepository).delete(userEntity);
    }

    @Test
    void testDeleteUser_NotFound() {
        UUID id = userModel.getId();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(id));
    }
}
