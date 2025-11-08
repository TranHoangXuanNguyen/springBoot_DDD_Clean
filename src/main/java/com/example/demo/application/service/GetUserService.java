package com.example.demo.application.service;

import com.example.demo.application.DTO.GetUserByIdRespone;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserId;
import com.example.demo.domain.user.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * CQRS Query Handler: xử lý yêu cầu đọc dữ liệu.
 * @Cacheable: lưu cache User vào Redis để giảm truy cập DB.
 */
@Service
public class GetUserService {

    private final UserRepository userRepository;
    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "userCache", key = "#userId")
    public GetUserByIdRespone handle(UUID userId) {
        User user = userRepository.findById(new UserId(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert domain → DTO ngay tại đây
        return GetUserByIdRespone.from(user);
    }
    public List<User> getUsers() {
        return  userRepository.findAll();
    }
}