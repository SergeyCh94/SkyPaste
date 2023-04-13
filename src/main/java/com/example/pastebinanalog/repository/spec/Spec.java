package com.example.pastebinanalog.repository.spec;

import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.model.Pastebin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class Spec {

    public static Specification<Pastebin> byTitle(String title){
        return StringUtils.hasText(title) ? (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("title"), title),
                        criteriaBuilder.equal(root.get("status"), Status.PUBLIC)
                ) : null;
    }

    public static Specification<Pastebin> byBody(String body){
        return StringUtils.hasText(body) ? (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("body"), "%" + body + "%"),
                        criteriaBuilder.equal(root.get("status"), Status.PUBLIC)
                ) : null;
    }
}
