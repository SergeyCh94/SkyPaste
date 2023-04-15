package com.example.pastebinanalog.service;

import com.example.pastebinanalog.dto.PasteCheck;
import com.example.pastebinanalog.dto.PastebinCreateDTO;
import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
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

    public String createPaste(PastebinCreateDTO pasteDTO, ExpirationTime time, Status pasteStatus) {
        Pastebin paste = pasteDTO.toPaste();
        paste.setLink(UUID.randomUUID().toString());
        paste.setExpiredTime(Instant.now().plus(time.getDuration()));
        paste.setStatus(pasteStatus);
        pastebinRepository.save(paste);
        return paste.getLink();
    }
    public List<PasteCheck> findAllPublicPaste() {
        return pastebinRepository.findAllByStatusPublic()
                .stream()
                .map(PasteCheck::fromPaste)
                .collect(Collectors.toList());
    }
    public PasteCheck findByLink(String link) {
        return PasteCheck.fromPaste(pastebinRepository.findAllByLinkLike(link));
    }
    public List<PasteCheck> findByTitleOrBody(String title, String body) {
        return pastebinRepository.findAll(Specification.where(byTitle(title).and(byBody(body))))
                .stream()
                .map(PasteCheck::fromPaste)
                .collect(Collectors.toList());
    }
}
