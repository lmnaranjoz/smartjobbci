package com.smartjob.cl.bci.common.mapper;

import com.smartjob.cl.bci.dto.request.UserRequestDTO;
import com.smartjob.cl.bci.dto.response.UserResponseDTO;
import com.smartjob.cl.bci.entity.UserEntity;
import com.smartjob.cl.bci.model.UserModel;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel UserRequestDTOToUserModel (UserRequestDTO source);

    List<UserModel> UserRequestDTOListToUserModelList(List<UserRequestDTO> source);

    UserResponseDTO UserModelToUserResponseDTO(UserModel source);

    List<UserResponseDTO> UserModelListToUserResponseDTOList(List<UserModel> source);

    UserEntity UserModelToUserEntity(UserModel source);

    UserModel UserEntityToUserModel(UserEntity source);

    List<UserModel> UserEntityListToUserModelList(List<UserEntity> source);
}
