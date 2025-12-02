package tn.esprit.angulartraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.angulartraining.dto.UserDto;
import tn.esprit.angulartraining.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.esprit.angulartraining.payload.ApiResponse;
import tn.esprit.angulartraining.mapper.UserMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.createUser(UserMapper.toEntity(userDto));
        UserDto dto = UserMapper.toDto(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User created successfully", dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        UserDto dto = UserMapper.toDto(userService.getUserById(id));
        return ResponseEntity.ok(new ApiResponse<>(true, "User retrieved", dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers().stream().map(UserMapper::toDto).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "All users retrieved", users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, UserMapper.toEntity(userDto));
        UserDto dto = UserMapper.toDto(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted", null));
    }
}
