package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.model.Pastebin;
import lombok.Data;

@Data
public class LinkDTO {
    private String link;
    public static LinkDTO fromPaste(Pastebin pastebin) {
        LinkDTO dto = new LinkDTO();
        dto.setLink(pastebin.getLink());
        return dto;
    }

    public PastebinCreateDTO toPaste() {
        PastebinCreateDTO dto = new PastebinCreateDTO();
        dto.setLink(this.getLink());
        return dto;
    }
}
