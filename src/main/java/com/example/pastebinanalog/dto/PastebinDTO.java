package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.model.Pastebin;
import lombok.Data;

import java.time.Instant;

@Data
public class PastebinDTO {

    private String title;
    private String body;
    private Instant publishedDate;

    public static PastebinDTO from(Pastebin pastebin) {
        PastebinDTO dto = new PastebinDTO();
        dto.setTitle(pastebin.getTitle());
        dto.setBody(pastebin.getBody());
        dto.setPublishedDate(pastebin.getPublishedDate());
        return dto;
    }
}