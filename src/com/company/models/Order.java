package com.company.models;

public class Order {
    private int orderNumber;
    private int customerId;
    private static int lastId = 0;
    private String data;
    private String deliveryAddress;
    private int deliveryCost;
    private DeliveryType delivery;
    private PaymentType payment;

    public Order(int orderNumber, int customerId, String data, String deliveryAddress, int deliveryCost, String delivery, String payment) {
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        if (orderNumber > lastId) {
            lastId = orderNumber;
        }
        this.data = data;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCost = deliveryCost;
        setDelivery(delivery);
        setPayment(payment);
    }

    public Order(int customerId, String data, String deliveryAddress, int deliveryCost, DeliveryType delivery, PaymentType payment) {
        this.orderNumber = lastId;
        lastId++;
        this.customerId = customerId;
        this.data = data;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCost = deliveryCost;
        this.delivery = delivery;
        this.payment = payment;
    }

/*    public Order(int customerId, String data, String deliveryAddress, int deliveryCost, String delivery, String payment) {
        this.orderNumber = lastId;
        lastId++;
        this.customerId = customerId;
        this.data = data;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCost = deliveryCost;
        setDelivery(delivery);
        setPayment(payment);
    }
*/

    public Order(int orderNumber, int customerId, String data, String deliveryAddress, int deliveryCost, DeliveryType delivery, PaymentType payment) {
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.data = data;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCost = deliveryCost;
        this.delivery = delivery;
        this.payment = payment;
    }

    public String getDelivery() {
        return delivery.toString();
    }

    public void setDelivery(String delivery) {
        this.delivery = DeliveryType.getType(delivery);
    }

    public String getPayment() {
        return payment.toString();
    }

    public void setPayment(String payment) {
        this.payment = PaymentType.getType(payment);
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
