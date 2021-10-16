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

    public static class CustomerDoesNotExist extends OrderException {
        public CustomerDoesNotExist(int s) {
            super(String.format("Заказчика с ID \"%s\" не существует", s));
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

    public List<Order> getAll() {
        return oRepository.getAll();
    }

    public List<Order> getAllByCustomerId(Order cid) throws CustomerDoesNotExist {
        Order x = oRepository.getByNumber(cid.getOrderNumber());
        if (x.getCustomerId() != cid.getCustomerId()) {
            throw new CustomerDoesNotExist(cid.getCustomerId());
        } else
            return oRepository.getAllByCustomerId(cid.getCustomerId());
    }
}
