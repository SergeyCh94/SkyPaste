package com.example.pastebinanalog.controller;

import com.example.pastebinanalog.dto.PasteCheck;
import com.example.pastebinanalog.dto.PastebinCreateDTO;
import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.service.PastebinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin")
public class PastebinController {


    private final PastebinService pastebinService;

    public PastebinController(PastebinService pastebinService) {
        this.pastebinService = pastebinService;
    }

    @PostMapping
    public String createPaste(
            @RequestBody PastebinCreateDTO pastebinDTO,
            @RequestParam ExpirationTime expirationTime,
            @RequestParam Status pasteStatus

    ) {
        return "http://my-awesome-pastebin.tld/" + pastebinService.createPaste(pastebinDTO, expirationTime, pasteStatus);
    }

    @GetMapping(value = "/last-ten")
    public List<PasteCheck> findAllPublic() {
        return pastebinService.findAllPublicPaste();
    }

    @GetMapping(value = "/{link}")
    public PasteCheck findByLink(@PathVariable String link) {
        return pastebinService.findByLink(link);
    }

    @GetMapping
    public List<PasteCheck> findByTitleOrBody(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return pastebinService.findByTitleOrBody(title, body);

    }
}
