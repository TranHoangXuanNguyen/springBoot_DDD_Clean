package com.example.demo.application.query;
import java.util.UUID;

/**
 * Query đại diện cho yêu cầu đọc dữ liệu User (theo ID).
 * Phần đọc (Query) tách khỏi phần ghi (Command).
 */
public record GetUserByIdQuery(UUID userId) {}