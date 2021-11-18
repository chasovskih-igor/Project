package com.company.repositories;

import com.company.models.ProductsInOrder;

import java.util.List;

public interface ProductsInOrderRepository {
    List<ProductsInOrder> getByOrderNumber(int o);

    void addProductInOrder(int o, int p, int c);

    void deleteProductFromOder(int o, int p);

}
