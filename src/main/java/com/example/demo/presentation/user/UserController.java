package com.example.demo.presentation.user;

import com.example.demo.application.DTO.GetUserByIdRespone;
import com.example.demo.application.command.CreateUserCommand;
import com.example.demo.application.service.CreateUserService;
import com.example.demo.application.service.GetUserService;
import com.example.demo.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller expose endpoint /api/users
 * Chỉ làm nhiệm vụ: nhận request → tạo command/query → gọi application service → trả response.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;
    private final GetUserService getUserService;

    public UserController(CreateUserService createUserService, GetUserService getUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateUserRequest req) {
        createUserService.handle(new CreateUserCommand(req.name(), req.email()));
        return ResponseEntity.ok("User created");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = getUserService.getUsers();
        List<UserResponse> dtos = users.stream()
                .map(UserResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdRespone> getUser(@PathVariable("id") UUID id) {
        GetUserByIdRespone userDto = getUserService.handle(id); // đã convert sang DTO trong service
        return ResponseEntity.ok(userDto);
    }

}