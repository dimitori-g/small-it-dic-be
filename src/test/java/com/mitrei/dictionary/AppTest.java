package com.mitrei.dictionary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.mitrei.dictionary.model.Dictionary;
import com.mitrei.dictionary.repository.DictionaryRepository;

@DataJpaTest
@ActiveProfiles("test")
public class AppTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DictionaryRepository userRepository;

    @Test
    public void testDictionary() {
        Dictionary testWord = new Dictionary(1L, "仪表盘", "", "dashboard");
        Dictionary managedWord = entityManager.merge(testWord);
        entityManager.flush();
        System.out.println(managedWord.getEnglish());
        Dictionary foundWord = userRepository.findById(testWord.getId()).orElse(null);
        assertThat(foundWord).usingRecursiveComparison().isEqualTo(managedWord);
    }
}