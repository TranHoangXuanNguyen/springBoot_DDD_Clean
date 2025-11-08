package com.example.demo.infra.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * Bean này định nghĩa cấu hình CHUNG cho tất cả cache.
     * Nó sẽ được sử dụng bởi cacheManager bên dưới.
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                // TTL mặc định 1 giờ
                .entryTtl(Duration.ofHours(1))
                // Không cache giá trị null
                .disableCachingNullValues()
                // Key serializer: String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // Value serializer: JSON (giải quyết lỗi Serializable)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    /**
     * Đây là BEAN QUAN TRỌNG NHẤT.
     * Bean này tạo ra CacheManager mà @Cacheable sẽ sử dụng.
     * Nó thay thế CacheManager mặc định của Spring Boot.
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // Cấu hình riêng cho từng cache name (nếu cần)
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // Cấu hình cho "userCache" sống 10 phút
        cacheConfigurations.put("userCache",
                cacheConfiguration().entryTtl(Duration.ofMinutes(10))
        );

        // Cấu hình cho "productCache" sống 1 ngày
        cacheConfigurations.put("productCache",
                cacheConfiguration().entryTtl(Duration.ofDays(1))
        );

        return RedisCacheManager.builder(connectionFactory)
                // Cấu hình mặc định cho tất cả cache (lấy từ bean ở trên)
                .cacheDefaults(cacheConfiguration())
                // Áp dụng các cấu hình riêng cho từng cache
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    /**
     * Cấu hình RedisTemplate (Dùng khi bạn tự inject và thao tác với Redis).
     * Bean này KHÔNG ảnh hưởng đến @Cacheable.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}