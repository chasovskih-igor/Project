package com.company.repositories;

import com.company.models.Order;
import com.company.models.Product;

import java.util.List;

public interface ProductsInOrderRepository {
    List<Product> getByOrderNumber(Order o);

    void addProductInOrder(Order o, Product p);

    void deleteProductFromOder(Order o, Product p);

}
