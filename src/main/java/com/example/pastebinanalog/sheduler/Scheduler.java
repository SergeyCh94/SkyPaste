package com.example.pastebinanalog.sheduler;

import com.example.pastebinanalog.model.Pastebin;
import com.example.pastebinanalog.repository.PastebinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Scheduler {
    private final PastebinRepository pastebinRepository;

    public Scheduler(PastebinRepository pastebinRepository) {
        this.pastebinRepository = pastebinRepository;
    }

    @Scheduled(fixedRateString = "${timer.rate-minutes}", timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void clearTokens() {
        log.info("Scheduler is work");
        pastebinRepository.deleteAllByExpiredTimeIsBefore(Instant.now());
    }
}