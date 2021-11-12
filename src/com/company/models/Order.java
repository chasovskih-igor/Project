package com.company.models;


public class Order {

    public enum DeliveryType {DeliveryToHouse, SelfPickUp}

    public enum PaymentType {Cash, ByCard}
    private int orderNumber;
    private int customerId;
    private String data;
    private String deliveryAddress;
    private int deliveryCost;
    private String delivery;
    private String payment;

    public Order(int orderNumber, int customerId, String data, String deliveryAddress, int deliveryCost, String delivery, String payment) {
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.data = data;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCost = deliveryCost;
        this.delivery = delivery;
        this.payment = payment;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
