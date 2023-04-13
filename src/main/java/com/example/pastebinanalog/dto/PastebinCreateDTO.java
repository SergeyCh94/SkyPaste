package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.model.Pastebin;
import lombok.Data;

@Data
public class PastebinCreateDTO {

    private String title;
    private String body;

    public Pastebin to() {
        Pastebin pastebin = new Pastebin();
        pastebin.setTitle(this.getTitle());
        pastebin.setBody(this.getBody());
        return pastebin;
    }
}
