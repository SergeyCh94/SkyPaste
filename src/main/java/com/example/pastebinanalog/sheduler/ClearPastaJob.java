package com.example.pastebinanalog.sheduler;

import com.example.pastebinanalog.repository.PastaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Component
public class ClearPastaJob {

    private final PastaRepository pastaRepository;

    public ClearPastaJob(PastaRepository pastaRepository) {
        this.pastaRepository = pastaRepository;
    }

    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void clearTokens() {
        log.info("Clear old pasta's");
        pastaRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }

}
