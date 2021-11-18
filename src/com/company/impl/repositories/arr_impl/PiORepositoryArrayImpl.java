package com.company.impl.repositories.arr_impl;

import com.company.models.ProductsInOrder;
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

    private final List<ProductsInOrder> pio = new ArrayList<>();

    @Override
    public void addProductInOrder(int o, int p, int c) {
        ProductsInOrder x = new ProductsInOrder(p, o, c);
        pio.add(x);
    }

    @Override
    public List<ProductsInOrder> getByOrderNumber(int o) {
        List<ProductsInOrder> t = new ArrayList<>();
        for (ProductsInOrder x : pio) {
            if (x.getOrderNumber() == o) {
                t.add(x);
            }
        }
        return t;
    }

    @Override
    public void deleteProductFromOder(int o, int p) {
        for (ProductsInOrder x : pio) {
            if (x.getOrderNumber() == o && x.getProductVendorCode() == p) {
                pio.remove(x);
                break;
            }
        }
    }
}
