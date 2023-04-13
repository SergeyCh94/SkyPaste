package com.example.pastebinanalog.controller;

import com.example.pastebinanalog.dto.PastebinCreateDTO;
import com.example.pastebinanalog.dto.PastebinDTO;
import com.example.pastebinanalog.dto.PastebinUrlDTO;
import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.service.PastebinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PastebinController {

    private final PastebinService pastebinService;

    public PastebinController(PastebinService pasteFieldService) {
        this.pastebinService = pasteFieldService;
    }

    @GetMapping
    public List<PastebinDTO> getPublicList() {
        return pastebinService.getPublicPastaList();
    }

    @GetMapping("/{hash}")
    public ResponseEntity<PastebinDTO> getByHash(@PathVariable String hash) {
        return ResponseEntity.ok(pastebinService.getPastaByHash(hash));
    }

    @PostMapping
    public PastebinUrlDTO addPasta(@RequestParam(name = "status") Status status,
                                   @RequestParam(name = "when to delete") ExpirationTime expirationTime,
                                   @RequestBody PastebinCreateDTO pastebinCreateDTO) {
        return pastebinService.createPasta(pastebinCreateDTO, status, expirationTime);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PastebinDTO>> searchBy(@RequestParam(required = false) String title,
                                                      @RequestParam(required = false) String body){
        return ResponseEntity.ok(pastebinService.search(title, body));
    }
}
