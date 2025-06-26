package com.example.codesage.model;

import lombok.Data;

@Data
public class Recommendation {
    private String issue;
    private String description;
    private String suggestion;
    private String example;
    private String note;
}
