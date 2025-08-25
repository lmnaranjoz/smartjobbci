package com.smartjob.cl.bci.controller;

import com.smartjob.cl.bci.common.mapper.UserMapper;
import com.smartjob.cl.bci.dto.request.UserRequestDTO;
import com.smartjob.cl.bci.dto.response.UserResponseDTO;
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "User API", description = "This API serve all functionality for management User")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Operation(description = "Create user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Creted"),
            @ApiResponse(responseCode = "400", description = "Bad requets"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userMapper.UserModelToUserResponseDTO(userService.createUser(userMapper.UserRequestDTOToUserModel(userRequestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @Operation(description = "Get all users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = userMapper.UserModelListToUserResponseDTOList(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @Operation(description = "Find user by ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getByID(@PathVariable String id) {

        UserModel user =  userService.getUserByID(UUID.fromString(id)).isPresent() ? userService.getUserByID(UUID.fromString(id)).get() : UserModel.builder().build();

        return ResponseEntity.ok(userMapper.UserModelToUserResponseDTO(user));
    }

    @Operation(description = "Update user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Bad requets"),
            @ApiResponse(responseCode = "404",description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updateUser = userMapper.UserModelToUserResponseDTO(userService.updateUser(UUID.fromString(id),userMapper.UserRequestDTOToUserModel(userRequestDTO)));

        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @Operation(description = "Delete user")
    @ApiResponses(value = {@ApiResponse(responseCode = "204",description = "Deleted"),
            @ApiResponse(responseCode = "404",description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) {

        userService.deleteUser(UUID.fromString(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
