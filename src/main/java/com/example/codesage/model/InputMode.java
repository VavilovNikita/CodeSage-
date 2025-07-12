package com.example.codesage.model;

import lombok.Getter;

@Getter
public enum InputMode {
    LOGS(" логов. "),
    PR(" Pull Request. "),
    CODE(" кода. ");

    private final String name;

    InputMode(String name) {
        this.name = name;
    }
}
