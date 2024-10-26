package com.beerrate.config;

import com.beerrate.DTO.BeerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;

@Configuration
@EnableCaching
@AllArgsConstructor
public class CacheConfig {
    private final ObjectMapper objectMapper;
    @Bean
    public LoggingCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, BeerDTO.class);
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()
                        ))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer(objectMapper)));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();

        return new LoggingCacheManager(redisCacheManager);
    }
}
