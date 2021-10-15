package com.company.repositories;

import com.company.models.Order;
import com.company.models.Product;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();// возможно удалить, если не понадобится

    Order getByNumber(int orderNumber);

    void add(Order o);

    void update(Order o);

    void delete(Order o);

    List<Order> getAllByCustomerId(int customerId);

    void copy(Order o, Order k);
}
