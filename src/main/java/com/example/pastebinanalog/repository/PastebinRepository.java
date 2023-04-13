package com.example.pastebinanalog.repository;

import com.example.pastebinanalog.model.Pastebin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PastebinRepository extends JpaRepository<Pastebin, Long> {

    Optional<Pastebin> findPastaByHash(String hash);

    void deleteAllByExpiredDateIsBefore(Instant now);

    @Query(value = "SELECT p FROM pastebin p WHERE p.status = 'PUBLIC' ORDER BY p.published_date DESC LIMIT 10",
            nativeQuery = true)
    List<Pastebin> findTenLastPasta();

    List<Pastebin> findAllBy(Specification<Pastebin> spec);
}