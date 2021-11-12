package com.company.impl.repositories.arr_impl;

import com.company.models.Order;
import com.company.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryArrayImpl implements OrderRepository {

    private static OrderRepositoryArrayImpl instance = null;

    public static OrderRepositoryArrayImpl getInstance() {
        if (instance == null)
            instance = new OrderRepositoryArrayImpl();
        return instance;
    }

    private final List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public void copy(Order o, Order k) {
        o.setCustomerId(k.getOrderNumber());
        o.setData(k.getData());
        o.setDeliveryAddress(k.getDeliveryAddress());
        o.setDeliveryCost(k.getDeliveryCost());
        o.setOrderNumber(k.getOrderNumber());
        o.setDelivery(k.getDelivery());
        o.setPayment(k.getPayment());
    }

    @Override
    public Order getByNumber(int orderNumber) {
        for (Order p : orders)
            if (p.getOrderNumber() == orderNumber)
                return p;
        return null;
    }

    @Override
    public void add(Order o) {
        orders.add(o);
    }

    @Override
    public void update(Order o) { //Обновляем данные заказа, передаём в качестве аргумента то, на что заменяем
        for (Order x : orders)
            if (o.getOrderNumber() == x.getOrderNumber()) {
                copy(x, o);
                break;
            } else orders.add(o);
    }

    @Override
    public void delete(Order o) {
        orders.remove(o);
    }

    @Override
    public List<Order> getAllByCustomerId(int customerId) { //Ищем список заказов по id клиента, если их нет возвращаем нулл
        List<Order> o = new ArrayList<>();
        for (Order x : orders)
            if (x.getCustomerId() == customerId) o.add(x);
        return o.size() == 0 ? null : o;
    }
}
