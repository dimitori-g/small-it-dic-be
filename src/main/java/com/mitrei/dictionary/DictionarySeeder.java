package com.mitrei.dictionary;

import com.mitrei.dictionary.model.Dictionary;
import com.mitrei.dictionary.repository.DictionaryRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class DictionarySeeder {

    @Autowired
    private DictionaryRepository dictionaryEntryRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void seedDictionaryEntries() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    resourceLoader.getResource("classpath:data/dictionary.csv").getInputStream(),
                    StandardCharsets.UTF_8
                )
            );

            CsvToBean<Dictionary> csvToBean = new CsvToBeanBuilder<Dictionary>(reader)
                .withType(Dictionary.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();
            System.out.println(csvToBean);

            List<Dictionary> dictionaryEntries = csvToBean.parse();
            
            dictionaryEntryRepository.saveAll(dictionaryEntries);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
