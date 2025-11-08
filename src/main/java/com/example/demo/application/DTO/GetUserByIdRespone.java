package com.example.demo.application.DTO;

import com.example.demo.domain.user.User;
import java.util.UUID;

/**
 * DTO để trả dữ liệu User ra ngoài hoặc cache vào Redis.
 * Chỉ chứa dữ liệu cần thiết, không chứa behavior domain.
 */
public record GetUserByIdRespone(
        UUID id,
        String name,
        String email
) {

    /**
     * Chuyển đổi domain User sang UserResponse DTO
     */
    public static GetUserByIdRespone from(User user) {
        return new GetUserByIdRespone(
                user.id().asUUID(),      // lấy UUID từ UserId value object
                user.name(),             // lấy name trực tiếp
                user.email().value()     // lấy String từ Email value object
        );
    }
}
