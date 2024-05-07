package com.mitrei.dictionary.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitrei.dictionary.model.Dictionary;
import com.mitrei.dictionary.repository.DictionaryRepository;
import com.mitrei.dictionary.repository.DictionarySpecifications;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Transactional
    public Dictionary createDicEntity(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public List<Dictionary> findAllDicEntities() {
        return dictionaryRepository.findAll();
    }

    public Dictionary findDicEntityById(Long id) {
        return dictionaryRepository.findById(id).orElse(null);
    }

    public Dictionary deleteWord(Long id) {
        Dictionary deleteWord = this.findDicEntityById(id);
        dictionaryRepository.deleteById(id);
        return deleteWord;
    }

    public List<Dictionary> searchDicEntityByText(String text, String lang) {
        Specification<Dictionary> spec = DictionarySpecifications.searchInLanguage(text, lang);
        return dictionaryRepository.findAll(spec);
    }

    public void backupDictionary() throws IOException {
        List<Dictionary> entries = dictionaryRepository.findAll();
        List<String[]> data = entries.stream()
            .map(entry -> new String[]{entry.getChinese(), entry.getReading(), entry.getEnglish()})
            .collect(Collectors.toList());

        Path path = Paths.get("./dictionary.bak.csv");

        try (
            CSVWriter writer = new CSVWriter(
                new FileWriter(path.toString()),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END)){
            String[] header = new String[] {"chinese", "reading", "english"};
            writer.writeNext(header);
            writer.writeAll(data);
        }
    }
}
