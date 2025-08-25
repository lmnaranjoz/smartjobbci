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
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;
    private final PasswordValidator passwordValidator;
    private final EmailValidator emailValidator;

    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper, PhoneMapper phoneMapper, PasswordValidator passwordValidator, EmailValidator emailValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.phoneMapper = phoneMapper;
        this.passwordValidator = passwordValidator;
        this.emailValidator = emailValidator;
    }

    @Transactional
    public UserModel createUser(UserModel userModel) {

        validateUser(userModel);
        validateExistsEmailUser(userModel);

        userModel.setCreatioDate(LocalDateTime.now());
        userModel.setUpdateDate(LocalDateTime.of(1970, 1, 1, 0, 0));
        userModel.setLastLogin(LocalDateTime.now());
        userModel.setIsActive(Boolean.TRUE);

        UserEntity userEntityToSave = userMapper.UserModelToUserEntity(userModel);
        if (userModel.getPhones() !=null) {
            List<PhoneEntity> phoneEntityList = addUserToPhonesList(userModel, userEntityToSave);
            userEntityToSave.setPhones(phoneEntityList);
        }

        UserEntity userEntitySaved = userRepository.save(userEntityToSave);

        return userMapper.UserEntityToUserModel(userEntitySaved);
    }

    public List<UserModel> getAllUsers() {

        return userMapper.UserEntityListToUserModelList(userRepository.findAll());
    }

    public Optional<UserModel> getUserByID(UUID id) {

        Optional<UserEntity> userEntityOpt = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString())));

        return userEntityOpt.map(userMapper::UserEntityToUserModel);
    }

    @Transactional
    public UserModel updateUser(UUID id,UserModel userModel){

        validateUser(userModel);

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        userEntity.setName(userModel.getName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setUpdateDate(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setPassword(userModel.getPassword());

        userEntity.getPhones().clear();

        if (userModel.getPhones() !=null) {

            userModel.getPhones().forEach(phoneModel -> {
                PhoneEntity phoneEntity = PhoneEntity.builder()
                        .number(phoneModel.getNumber())
                        .citycode(phoneModel.getCitycode())
                        .contrycode(phoneModel.getContrycode())
                        .build();
                phoneEntity.setUser(userEntity);
                userEntity.getPhones().add(phoneEntity);
            });

        }

        return userMapper.UserEntityToUserModel(userRepository.save(userEntity));
    }

    @Transactional
    public void deleteUser(UUID id) {

        UserEntity userEntityOpt = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        userRepository.delete(userEntityOpt);
    }

    private void validateUser(UserModel userModel)  {

        if (!passwordValidator.isValidPassword(userModel.getPassword())) {
            throw new PasswordException("");
        }

        if (!emailValidator.isValidEmail(userModel.getEmail())) {
            throw new EmailException(EmailException.INVALID_EMAIL + userModel.getEmail());
        }
    }

    private void validateExistsEmailUser(UserModel userModel)  {

        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new EmailException(EmailException.EXISTS_EMAIL);
        }
    }

    private List<PhoneEntity> addUserToPhonesList(UserModel userModel, UserEntity userToSave) {
        List<PhoneEntity> phonesList = phoneMapper.PhoneModelListToPhoneEntityList(userModel.getPhones());
        phonesList.forEach(phone -> {
            phone.setUser(userToSave);
        });
        return phonesList;
    }

}
