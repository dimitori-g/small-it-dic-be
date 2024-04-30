package com.mitrei.dictionary.repository;

import org.springframework.data.jpa.domain.Specification;

import com.mitrei.dictionary.model.Dictionary;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class DictionarySpecifications {

    public static Specification<Dictionary> searchInLanguage(String text, String lang) {
        return (Root<Dictionary> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            String likePattern = "%" + text.toLowerCase() + "%";
            Predicate predicate;
            switch (lang) {
                case "ch":
                    predicate = cb.like(cb.lower(root.get("chinese")), likePattern);
                    break;
                case "rd":
                    predicate = cb.like(cb.lower(root.get("reading")), likePattern);
                    break;
                case "en":
                default:
                    predicate = cb.like(cb.lower(root.get("english")), likePattern);
            }
            return predicate;
        };
    }
}
