package com.beerrate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

public class LoggingCacheManager implements CacheManager {
    private final CacheManager delegate;
    private static final Logger logger = LoggerFactory.getLogger(LoggingCacheManager.class);

    public LoggingCacheManager(CacheManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = delegate.getCache(name);
        return (cache == null) ? null : new LoggingCache(cache);
    }

    @Override
    public Collection<String> getCacheNames() {
        return delegate.getCacheNames();
    }
}
