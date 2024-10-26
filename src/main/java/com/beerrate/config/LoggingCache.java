package com.beerrate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public class LoggingCache implements Cache {
    private final Cache delegate;
    private static final Logger logger = LoggerFactory.getLogger(LoggingCache.class);

    public LoggingCache(Cache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Object getNativeCache() {
        return delegate.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper value = delegate.get(key);
        if (value != null) {
            logger.info("Cache hit for key: {}", key);
        } else {
            logger.info("Cache miss for key: {}", key);
        }
        return value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        T value = delegate.get(key, type);
        if (value != null) {
            logger.info("Cache hit for key: {}", key);
        } else {
            logger.info("Cache miss for key: {}", key);
        }
        return value;
    }


    @Override
    public void put(Object key, Object value) {
        logger.info("Cache put for key: {}", key);
        delegate.put(key, value);
    }

    // Logging for the get with Callable<T> method
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        try {
            T value = delegate.get(key, valueLoader);
            if (value != null) {
                logger.info("Cache loaded for key: {}", key);
            } else {
                logger.info("Cache miss and load for key: {}", key);
            }
            return value;
        } catch (Exception e) {
            logger.error("Cache load error for key: {}", key, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void evict(Object key) {
        logger.info("Cache evict for key: {}", key);
        delegate.evict(key);
    }

    @Override
    public void clear() {
        logger.info("Cache clear for cache: {}", delegate.getName());
        delegate.clear();
    }
}
