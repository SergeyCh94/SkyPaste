package com.example.pastebinanalog.model;

import com.example.pastebinanalog.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Getter
@Setter
public class Pastebin {
    @Id
    private String link;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Instant expiredTime;
    private Instant creationTime = Instant.now();

}