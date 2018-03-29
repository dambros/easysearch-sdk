package br.com.dataeasy.easysearch.sdk.model;

public enum Flag {

    INDEXED(1),
    CATEGORIZED(2),
    STORED(4),
    ESSENTIAL(8),
    INNER(16),
    SORTABLE(32),
    AUTOCOMPLETE(64)
    ;

    private int value;

    Flag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
