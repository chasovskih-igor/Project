package com.company.models;

public enum PaymentType {
    Cash, ByCard;

    public static PaymentType getType(String value) {
        return switch(value.toUpperCase().charAt(0)) {
            case 'C' -> Cash;
            case 'B' -> ByCard;
            default -> null;
        };
    }
}
