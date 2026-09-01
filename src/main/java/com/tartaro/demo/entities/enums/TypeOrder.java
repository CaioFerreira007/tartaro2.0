package com.tartaro.demo.enums;

public enum TypeOrder {
    DELIVERED(1),
    RETIRED(2);
    private final int value;
    TypeOrder(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static TypeOrder valueOf(int value) {
        for(TypeOrder status : TypeOrder.values()) {
        if (status.value == value) {
            return status;
        }
        }
        throw new IllegalArgumentException("Invalid TypeOrder code");

    }

}
