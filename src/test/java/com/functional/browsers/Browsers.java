package com.functional.browsers;

public enum Browsers {
    FIREFOX,
    CHROME,
    IE;

    public static Browsers browserForName(String browser) throws IllegalArgumentException {
        for (Browsers browsers : values()) {
            if (browsers.toString().equalsIgnoreCase(browser)) {
                return browsers;
            }
        }
        throw browserNotFound(browser);
    }

    private static IllegalArgumentException browserNotFound(String browser) {
        return new IllegalArgumentException("Invalid browser [" + browser + "]");
    }
}