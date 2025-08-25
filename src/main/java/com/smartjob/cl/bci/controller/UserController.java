package com.smartjob.cl.bci.controller;

import com.smartjob.cl.bci.common.mapper.UserMapper;
import com.smartjob.cl.bci.dto.request.UserRequestDTO;
import com.smartjob.cl.bci.dto.response.UserResponseDTO;
import com.smartjob.cl.bci.model.UserModel;
import com.smartjob.cl.bci.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

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


    @Operation(summary  = "Create user", description = "Allows you to create a user and their phone list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "User Example",
                                    summary = "Example of a successful response",
                                    value = """
                                        {
                                          "name": "Luis Miguel Naranjo",
                                          "email": "lmnz@gmail.com",
                                          "phones": [
                                            {
                                              "number": "(+57)301 239-84-39",
                                              "citycode": 23,
                                              "contrycode": 57
                                            }
                                          ],
                                          "id": "2aed95b8-b3f6-42db-b8de-e36407b2e69e",
                                          "created": "2025-08-25T13:37:16.9872176",
                                          "modified": "1970-01-01T00:00:00",
                                          "last_login": "2025-08-25T13:37:16.9872176",
                                          "token": "98e74534-ed02-47b5-9cac-f128d869ed52",
                                          "isactive": true
                                          }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Field validation error e.g. (invalid email, password, etc)",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error Validacion",
                                    summary = "Example of validation error",
                                    value = """
                                        {
                                          "message": "Validation error in submitted fields"
                                        }
                                        """
                            )
                    )
            )
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userMapper.UserModelToUserResponseDTO(userService.createUser(userMapper.UserRequestDTOToUserModel(userRequestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @Operation(description = "Get all users")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(
                    responseCode = "200",
                    description = "returns a list of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "User List Example",
                                    summary = "Example of a successful response",
                                    value = """
                                        [
                                             {
                                               "name": "Luis Miguel Naranjo",
                                               "email": "lmnz@gmail.com",
                                               "phones": [
                                                 {
                                                   "number": "(+57)301 239-84-39",
                                                   "citycode": 23,
                                                   "contrycode": 57
                                                 }
                                               ],
                                               "id": "0f3b943d-67c9-4e04-88f3-435c46c7a4ed",
                                               "created": "2025-08-25T15:01:36.862547",
                                               "modified": "1970-01-01T00:00:00",
                                               "last_login": "2025-08-25T15:01:36.862547",
                                               "token": "9a5977c8-8025-43e3-af3a-8ad015f75cee",
                                               "isactive": true
                                             }
                                        ]
                                    """
                            )
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> users = userMapper.UserModelListToUserResponseDTOList(userService.getAllUsers());
        return ResponseEntity.ok(users);
    }

    @Operation(description = "Find user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "User found Example",
                                    summary = "Example of a successful response",
                                    value = """
                                        {
                                             "name": "Luis Miguel Naranjo",
                                             "email": "lmnz@gmail.com",
                                             "phones": [
                                               {
                                                 "number": "(+57)301 239-84-39",
                                                 "citycode": 23,
                                                 "contrycode": 57
                                               }
                                             ],
                                             "id": "2bbf29d9-2cfb-49f9-b11f-d0b857e722a4",
                                             "created": "2025-08-25T15:13:24.696385",
                                             "modified": "1970-01-01T00:00:00",
                                             "last_login": "2025-08-25T15:13:24.696385",
                                             "token": "15df7f97-4755-4562-b5db-c6f2ef7350a2",
                                             "isactive": true
                                         }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "User not found Example",
                                    summary = "Example of User not found",
                                    value = """
                                        {
                                          "message": "User with ID 2bbf29d9-2cfb-49f9-b11f-d0b857e722a5 not found"
                                        }
                                        """
                            )
                    )
            )
    })
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getByID(@PathVariable String id) {

        UserModel user =  userService.getUserByID(UUID.fromString(id)).isPresent() ? userService.getUserByID(UUID.fromString(id)).get() : UserModel.builder().build();

        return ResponseEntity.ok(userMapper.UserModelToUserResponseDTO(user));
    }

    @Operation(description = "Update user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Modified user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Modified User Example",
                                    summary = "Example of a successful response",
                                    value = """
                                        {
                                             "name": "Luis Miguel Naranjo Z.",
                                             "email": "lmnz58@gmail.com",
                                             "phones": [
                                               {
                                                 "number": "(+58)301 239-84-39",
                                                 "citycode": 28,
                                                 "contrycode": 58
                                               }
                                             ],
                                             "id": "91779dcc-5969-4fba-aabf-853aff4aa9a3",
                                             "created": "2025-08-25T15:31:10.801401",
                                             "modified": "2025-08-25T15:32:04.1341323",
                                             "last_login": "2025-08-25T15:32:04.1341323",
                                             "token": "9c23f068-1fe6-437b-8717-119b850855a5",
                                             "isactive": true
                                       }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Field validation error e.g. (invalid email, password, etc)",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error Validacion",
                                    summary = "Example of validation error",
                                    value = """
                                        {
                                          "message": "Validation error in submitted fields"
                                        }
                                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "User not found Example",
                                    summary = "Example of User not found",
                                    value = """
                                        {
                                          "message": "User with ID 2bbf29d9-2cfb-49f9-b11f-d0b857e722a5 not found"
                                        }
                                        """
                            )
                    )
            )
    })
    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updateUser = userMapper.UserModelToUserResponseDTO(userService.updateUser(UUID.fromString(id),userMapper.UserRequestDTOToUserModel(userRequestDTO)));

        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @Operation(description = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted user"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "User not found Example",
                                    summary = "Example of User not found",
                                    value = """
                                        {
                                          "message": "User with ID 2bbf29d9-2cfb-49f9-b11f-d0b857e722a5 not found"
                                        }
                                        """
                            )
                    )
            )
    })
    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) {

        userService.deleteUser(UUID.fromString(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
