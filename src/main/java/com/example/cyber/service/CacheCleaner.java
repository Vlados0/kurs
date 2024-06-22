package com.example.cyber.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheCleaner {

    @CacheEvict(cacheNames = "products")
    public void cleanCache() {

    }

}
