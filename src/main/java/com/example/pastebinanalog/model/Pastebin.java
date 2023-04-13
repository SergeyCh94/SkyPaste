package com.example.pastebinanalog.model;

import com.example.pastebinanalog.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Pastebin {

    private String title;
    private String body;

    @Id
    private String hash;

    private Instant publishedDate;
    private Instant expiredDate;

    @Enumerated(EnumType.STRING)
    private Status status;

}
