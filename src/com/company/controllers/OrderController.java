package com.company.controllers;

import com.company.models.Order;
import com.company.repositories.OrderRepository;

import java.util.List;

public class OrderController {
    public static class OrderException extends Exception {
        public OrderException(String message) {
            super(message);
        }
    }

    public static class OrderAlreadyExists extends OrderException {
        public OrderAlreadyExists(int s) {
            super(String.format("Заказ \"%s\" уже существует", s));
        }
    }

    public static class OrderDoesNotExist extends OrderException {
        public OrderDoesNotExist(int s) {
            super(String.format("Заказа с номером \"%s\" не существует", s));
        }
    }

    private OrderRepository oRepository;

    public OrderController(OrderRepository oRepository) {
        this.oRepository = oRepository;
    }

    public void addNewOrder(Order o) throws OrderAlreadyExists {
        Order x = oRepository.getByNumber(o.getOrderNumber());
        if (x != null) {
            throw new OrderAlreadyExists(o.getOrderNumber());
        }
        oRepository.add(o);
    }

    public Order getByNumber(int n) throws OrderDoesNotExist {
        Order x = oRepository.getByNumber(n);
        if (x == null) {
            throw new OrderDoesNotExist(n);
        }
        return x;
    }

    public List<Order> getAllByCustomerId(int cid) throws CustomerController.CustomerDoesNotExists {
        Order x = oRepository.getByNumber(cid);
        if (x == null || x.getCustomerId() == cid) {
            throw new CustomerController.CustomerDoesNotExists();
        } else
            return oRepository.getAllByCustomerId(cid);
    }
    public void removeOrder(int n) throws OrderDoesNotExist{
        Order x = oRepository.getByNumber(n);
        if (x == null) throw new OrderDoesNotExist(n);
        oRepository.delete(x);
    }

    public void update(Order o) {
        oRepository.update(o);
    }
}
