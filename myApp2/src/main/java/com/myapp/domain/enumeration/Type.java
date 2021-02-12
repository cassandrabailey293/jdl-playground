package com.myapp.domain.enumeration;

/**
 * The Type enumeration.
 */
public enum Type {
    KEY("value"),
    JUSTKEY;

    private String value;

    Type() {}

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
