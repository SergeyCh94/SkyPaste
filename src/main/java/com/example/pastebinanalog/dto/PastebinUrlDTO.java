package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.model.Pastebin;
import lombok.Data;

@Data
public class PastebinUrlDTO {

    private String url;

    public static PastebinUrlDTO from(Pastebin pastebin) {
        PastebinUrlDTO url = new PastebinUrlDTO();
        url.setUrl("my-awesome-pastebin.tld/" + pastebin.getHash());
        return url;
    }

}
