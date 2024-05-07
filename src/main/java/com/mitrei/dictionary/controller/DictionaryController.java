package com.mitrei.dictionary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitrei.dictionary.model.Dictionary;
import com.mitrei.dictionary.model.api.ApiError;
import com.mitrei.dictionary.model.api.DictionaryResponseMany;
import com.mitrei.dictionary.model.api.DictionaryResponseOne;
import com.mitrei.dictionary.service.DictionaryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping
    public Dictionary addWord(@RequestBody Dictionary word) {
        Dictionary newWord = new Dictionary();
        newWord.setChinese(word.getChinese());
        newWord.setReading(word.getReading());
        newWord.setEnglish(word.getEnglish());
        return dictionaryService.createDicEntity(newWord);
    }

    @PutMapping
    public ResponseEntity<DictionaryResponseOne> updateWordById(@RequestBody Dictionary word) {
        Long updateId = word.getId();
        Dictionary newWord = dictionaryService.findDicEntityById(updateId);
        if (newWord != null) {
            newWord.setChinese(word.getChinese());
            newWord.setReading(word.getReading());
            newWord.setEnglish(word.getEnglish());
            dictionaryService.createDicEntity(newWord);
            return ResponseEntity.ok().body(new DictionaryResponseOne(newWord));
        }
        ApiError apiError = new ApiError(
                404,
                "Word not founded",
                "/api/dictionary"
            );
        return ResponseEntity.status(apiError.getStatus()).body(new DictionaryResponseOne(apiError));
    }

    @GetMapping
    public ResponseEntity<DictionaryResponseMany> getAllWords() {
        List<Dictionary> allWords =  dictionaryService.findAllDicEntities();
        return ResponseEntity.ok(new DictionaryResponseMany(allWords));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryResponseOne> getWordById(@PathVariable Long id) {
        Dictionary word = dictionaryService.findDicEntityById(id);
        if (word != null) {
            return ResponseEntity.ok().body(new DictionaryResponseOne(word));
        } else {
            ApiError apiError = new ApiError(
                404,
                "Word not founded",
                "/api/dictionary/{id}"
            );
            return ResponseEntity.status(apiError.getStatus()).body(new DictionaryResponseOne(apiError));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWord(@PathVariable Long id) {
        try {
            dictionaryService.deleteWord(id);
            return ResponseEntity.ok("Word deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<DictionaryResponseMany> searchWord(
        @RequestParam String text,
        @RequestParam(defaultValue = "en") String lang) {
        if (text == null || text.trim().isEmpty()) {
            ApiError apiError = new ApiError(
                200,
                "Text cannot be empty",
                "/api/dictionary/search"
            );
            return ResponseEntity.status(apiError.getStatus()).body(new DictionaryResponseMany(apiError));
        }
        List<Dictionary> dicEntities = dictionaryService.searchDicEntityByText(text, lang);
        return ResponseEntity.ok(new DictionaryResponseMany(dicEntities));
    }

    @GetMapping("/random/{n}")
    public ResponseEntity<DictionaryResponseMany> getRandomWords(@PathVariable("n") int n) {
        List<Dictionary> words = dictionaryService.findRandomWords(n);
        if (words.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new DictionaryResponseMany(words));
    }

    @GetMapping("/backup")
    public ResponseEntity<String> backupDictionary() {
        try {
            dictionaryService.backupDictionary();
            return ResponseEntity.ok("Backup created successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
