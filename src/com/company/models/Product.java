package com.company.models;

public class Product {
    private int vendorCode;
    private boolean presence;
    private String technicType;
    private String brand;
    private String model;
    private int price;



    public Product(int vendorCode, boolean presence, String technicType, String brand, String model, int price) {
        this.vendorCode = vendorCode;
        this.presence = presence;
        this.technicType = technicType;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public void setVendorCode(int vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public void setTechnicType(String technicType) {
        this.technicType = technicType;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPresence() {
        return presence;
    }

    public String getTechnicType() {
        return technicType;
    }

    public int getPrice() {
        return price;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
