package com.example.pastebinanalog.repository.spec;

import com.example.pastebinanalog.model.Pastebin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class Spec {
    public static Specification<Pastebin> byTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("title"), title);
        };
    }
    public static Specification<Pastebin> byBody(String body) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(body)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("body"), body);
        };
    }
}
