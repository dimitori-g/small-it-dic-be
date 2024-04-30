package com.mitrei.dictionary.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private String path;
}
