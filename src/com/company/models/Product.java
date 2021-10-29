package com.company.models;

public class Product {
    private int vendorCode;
    private boolean presence;
    private String technicType;
    private String brand;
    private String model;
    private int price;
    private int weight;
    private int height;
    private int lenght;
    private int width;
    private String description;


    public Product(int vendorCode, boolean presence, String technicType, String brand, String model, int price, int weight, int height, int lenght, int width, String description) {
        this.vendorCode = vendorCode;
        this.presence = presence;
        this.technicType = technicType;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.weight = weight;
        this.height = height;
        this.lenght = lenght;
        this.width = width;
        this.description = description;
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getLenght() {
        return lenght;
    }

    public int getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
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
