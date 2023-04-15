package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.model.Pastebin;
import lombok.Data;

@Data
public class PasteCheck {
    private String link;
    private String body;
    private String title;



    public static PasteCheck fromPaste(Pastebin pastebin) {
        PasteCheck dto = new PasteCheck();
        dto.setLink(pastebin.getLink());
        dto.setBody(pastebin.getBody());
        dto.setTitle(pastebin.getTitle());
        return dto;
    }
}
