package com.company.impl.repositories.arr_impl;

import com.company.models.Order;
import com.company.models.Product;
import com.company.repositories.ProductsInOrderRepository;


import java.util.ArrayList;
import java.util.List;

public class PiORepositoryArrayImpl implements ProductsInOrderRepository {

    private static PiORepositoryArrayImpl instance = null;

    public static PiORepositoryArrayImpl getInstance() {
        if (instance == null)
            instance = new PiORepositoryArrayImpl();
        return instance;
    }


    @Override
    public List<Product> getByOrderNumber(Order o) {
        o.getOrderNumber()
    }

    @Override
    public void addProductInOrder(Order o, Product p) {

    }

    @Override
    public void deleteProductFromOder(Order o, Product p) {

    }
}
