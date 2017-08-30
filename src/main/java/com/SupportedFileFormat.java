package com;

public enum SupportedFileFormat {
    Excel("xlsx"),
    CSV("csv"),
    ANY("")    ;

    private final String name;

    private SupportedFileFormat(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
