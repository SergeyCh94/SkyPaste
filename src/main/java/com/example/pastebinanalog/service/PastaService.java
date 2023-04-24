package com.example.pastebinanalog.service;

import com.example.pastebinanalog.cryptograph.RandomHashGenerator;
import com.example.pastebinanalog.dto.PastaCreateDTO;
import com.example.pastebinanalog.dto.PastaDTO;
import com.example.pastebinanalog.dto.PastaUrlDTO;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.exception.PastaNotFoundException;
import com.example.pastebinanalog.model.Pasta;
import com.example.pastebinanalog.repository.PastaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PastaService {

    private final PastaRepository pastaRepository;

    public PastaService(PastaRepository pastaRepository) {
        this.pastaRepository = pastaRepository;
    }


    public PastaUrlDTO createPasta(PastaCreateDTO pastaCreateDTO) {
        Pasta pasta = pastaCreateDTO.to();

        pasta.setPublishedDate(Instant.now());
        pasta.setExpiredDate(pasta.getPublishedDate()
                                                    .plus(pastaCreateDTO.getExpirationTime().getTime(),
                                                          pastaCreateDTO.getExpirationTime().getUnit()));
        pasta.setHash(RandomHashGenerator.generateHash());

        pastaRepository.save(pasta);
        return PastaUrlDTO.from(pasta);
    }

    public PastaDTO getPastaByHash(String hash) {
        Pasta pasta = pastaRepository.findPastaByHashAndExpiredDateIsAfter(hash, Instant.now())
                                     .orElseThrow(PastaNotFoundException::new);

        return PastaDTO.from(pasta);
    }

    public List<PastaDTO> getPublicPastaList() {
        return pastaRepository.findTop10ByStatusAndExpiredDateAfterOrderByPublishedDateDesc(Status.PUBLIC, Instant.now())
                .stream()
                .map(PastaDTO::from)
                .collect(Collectors.toList());
    }

    public List<PastaDTO> searchBy(String title, String body) {
        return pastaRepository.findAllByTitleContainsOrBodyContains(Status.PUBLIC, title, body)
                .stream()
                .map(PastaDTO::from)
                .collect(Collectors.toList());
    }
}
