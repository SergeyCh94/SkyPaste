package com.example.pastebinanalog.service;

import com.example.pastebinanalog.dto.PastebinCreateDTO;
import com.example.pastebinanalog.dto.PastebinDTO;
import com.example.pastebinanalog.dto.PastebinUrlDTO;
import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.exception.PastebinNotFoundException;
import com.example.pastebinanalog.model.Pastebin;
import com.example.pastebinanalog.repository.PastebinRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.pastebinanalog.repository.spec.Spec.byBody;
import static com.example.pastebinanalog.repository.spec.Spec.byTitle;

@Service
public class PastebinService {

    private final PastebinRepository pastebinRepository;

    public PastebinService(PastebinRepository pastebinRepository) {
        this.pastebinRepository = pastebinRepository;
    }


    public PastebinUrlDTO createPasta(PastebinCreateDTO pastebinCreateDTO, Status status, ExpirationTime expirationTime) {
        Pastebin pastebin = pastebinCreateDTO.to();
        pastebin.setStatus(status);
        pastebin.setPublishedDate(Instant.now());

        if (expirationTime == ExpirationTime.UNLIMITED) {
            pastebin.setExpiredDate(null);
        } else {
            pastebin.setExpiredDate(Instant.now().plus(expirationTime.getTime(),
                    expirationTime.getUnit()));
        }

        pastebin.setHash(UUID.randomUUID().toString().substring(0, 7));
        pastebinRepository.save(pastebin);
        return PastebinUrlDTO.from(pastebin);
    }

    public PastebinDTO getPastaByHash(String hash) {
        Pastebin pastebin = pastebinRepository.findPastaByHash(hash).orElseThrow(PastebinNotFoundException::new);
        return PastebinDTO.from(pastebin);
    }

    public List<PastebinDTO> getPublicPastaList() {
        return pastebinRepository.findTenLastPasta()
                .stream()
                .map(PastebinDTO::from)
                .collect(Collectors.toList());
    }

    public List<PastebinDTO> search(String title, String body) {
        return pastebinRepository.findAllBy(Specification.where(
                                byTitle(title))
                        .and(byBody(body)))
                .stream()
                .map(PastebinDTO::from)
                .collect(Collectors.toList());
    }
}
