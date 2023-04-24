package com.example.pastebinanalog.dto;

import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.model.Pasta;
import lombok.Data;

@Data
public class PastaCreateDTO {

    private String title;
    private String body;
    private Status status;
    private ExpirationTime expirationTime;

    public Pasta to() {
        Pasta pasta = new Pasta();
        pasta.setTitle(this.getTitle());
        pasta.setBody(this.getBody());
        pasta.setStatus(this.getStatus());
        return pasta;
    }

}
