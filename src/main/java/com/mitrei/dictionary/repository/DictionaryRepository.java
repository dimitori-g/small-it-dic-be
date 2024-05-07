package com.mitrei.dictionary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.mitrei.dictionary.model.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long>, JpaSpecificationExecutor<Dictionary> {
    @Query(value = "SELECT * FROM dictionary ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Dictionary> findRandomWords(int count);
}
