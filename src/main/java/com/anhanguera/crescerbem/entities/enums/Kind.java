package com.anhanguera.crescerbem.entities.enums;

import lombok.Getter;

@Getter
public enum Kind {
    MOM("Mom"),
    DAD("Dad");

    private String description;

    Kind(String description) {
        this.description = description;
    }
}
