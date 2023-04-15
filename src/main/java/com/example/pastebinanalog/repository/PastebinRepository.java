package com.example.pastebinanalog.repository;

import com.example.pastebinanalog.model.Pastebin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PastebinRepository extends JpaRepository<Pastebin, String>, JpaSpecificationExecutor<Pastebin> {
    @Query(value = "select * from paste p where p.status in ('PUBLIC') order by creation_time desc limit 10", nativeQuery = true)
    List<Pastebin> findAllByStatusPublic();
    Pastebin findAllByLinkLike(String link);
    @Override
    List<Pastebin> findAll(Specification<Pastebin> specification);
    void deleteAllByExpiredTimeIsBefore(Instant now);
}