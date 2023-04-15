package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.model.Pastebin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Data
public class PastebinCreateDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String link;
    private String body;
    private String title;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant expiredTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private Status status;
    public static PastebinCreateDTO fromPaste(Pastebin pastebin) {
        PastebinCreateDTO dto = new PastebinCreateDTO();
        dto.setLink(pastebin.getLink());
        dto.setBody(pastebin.getBody());
        dto.setTitle(pastebin.getTitle());
        dto.setExpiredTime(pastebin.getExpiredTime());
        dto.setStatus(pastebin.getStatus());
        return dto;
    }

    public Pastebin toPaste() {
        Pastebin paste = new Pastebin();
        paste.setLink(this.getLink());
        paste.setBody(this.getBody());
        paste.setTitle(this.getTitle());
        paste.setExpiredTime(this.getExpiredTime());
        paste.setStatus(this.getStatus());
        return paste;
    }
}
