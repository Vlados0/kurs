package com.example.cyber.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheScheduler {

    private final CacheCleaner cacheCleaner;

    @Scheduled(fixedDelay = 60000L)
    public void cleanCache() {
        cacheCleaner.cleanCache();
        log.info("Cache cleaned");
    }
}