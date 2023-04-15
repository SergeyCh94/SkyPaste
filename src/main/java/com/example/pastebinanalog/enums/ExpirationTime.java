package com.example.pastebinanalog.enums;

import lombok.Getter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Getter
public enum ExpirationTime {
    TEN_MIN(Duration.ofMinutes(10)),
    ONE_HOUR(Duration.ofHours(1)),
    THREE_HOUR(Duration.ofHours(3)),
    ONE_DAY(Duration.ofDays(1)),
    ONE_WEEK(Duration.ofDays(7)),
    ONE_MONTH(Duration.ofDays(30)),
    INFINITY(Duration.ofDays(100000));

    private final Duration duration;

    ExpirationTime(Duration duration) {
        this.duration = duration;
    }
}
