package com.example.pastebinanalog.sheduler;

import com.example.pastebinanalog.repository.PastebinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Component
public class ClearPastebinJob {

    private final PastebinRepository pastebinRepository;

    public ClearPastebinJob(PastebinRepository pastebinRepository) {
        this.pastebinRepository = pastebinRepository;
    }

    @Scheduled(fixedRate = 60_000)
    @Transactional
    public void clearTokens() {
        log.info("Clear old pasta's");
        pastebinRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }

}