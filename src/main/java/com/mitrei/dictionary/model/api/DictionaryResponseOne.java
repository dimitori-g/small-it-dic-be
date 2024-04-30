package com.mitrei.dictionary.model.api;

import com.mitrei.dictionary.model.Dictionary;

public class DictionaryResponseOne extends ApiResponse<Dictionary> {
    public DictionaryResponseOne(Dictionary word) {
        super(word, new ApiMetaData());
    }

    public DictionaryResponseOne(ApiError error) {
        super(error, new ApiMetaData());
    }
}
