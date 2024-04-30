package com.mitrei.dictionary.model.api;

import java.time.Instant;

public class ApiMetaData {
    private Instant timestamp;

    public ApiMetaData() {
        this.timestamp = Instant.now();
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

