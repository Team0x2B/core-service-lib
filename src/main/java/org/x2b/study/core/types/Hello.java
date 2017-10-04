package org.x2b.study.core.types;

public class Hello {
    private String value;

    public Hello(String foo) {
        if (foo != null) {
            System.out.println(foo); //TODO: don't system.out in production
        }
        this.value = "rip";
    }

    public String getValue() {
        return "just testing";
    }
}
