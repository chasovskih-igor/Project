package com.company.models;

public class Customer {
    private int id;
    private static int lastId = 0;
    private long phoneNumber;
    private String surname;
    private String name;
    private String patronymic;
    private String address;
    private String e_mail;

    public Customer(int id, String name, String surname, String patronymic, long phoneNumber, String address, String e_mail) {
        this.id = id;
        if (id > lastId) {
            lastId = id;
        }
        this.phoneNumber = phoneNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.e_mail = e_mail;
    }

    public Customer(String name, String surname, String patronymic, long phoneNumber, String address, String e_mail) {
        this.id = lastId;
        lastId++;
        this.phoneNumber = phoneNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.e_mail = e_mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
