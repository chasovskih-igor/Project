package com.company.models;

public enum DeliveryType {
    DeliveryToHouse, SelfPickUp;

    public static DeliveryType getType(String value) {
         return switch(value.toUpperCase().charAt(0)) {
           case 'D' -> DeliveryToHouse;
           case 'S' -> SelfPickUp;
           default -> null;
        };
    }
}
