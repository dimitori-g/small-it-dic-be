package com.mitrei.dictionary.model.api;

import java.util.List;

import com.mitrei.dictionary.model.Dictionary;

public class DictionaryResponseMany extends ApiResponse<List<Dictionary>> {
    public DictionaryResponseMany(List<Dictionary> words) {
        super(words, new ApiMetaData());
    }

    public DictionaryResponseMany(ApiError error) {
        super(error, new ApiMetaData());
    }
}
