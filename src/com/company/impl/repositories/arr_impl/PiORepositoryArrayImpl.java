package com.company.impl.repositories.arr_impl;

import com.company.models.Order;
import com.company.models.Product;
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
    public void addProductInOrder(Order o, Product p, int c) {
        ProductsInOrder x = new ProductsInOrder(p, o, c);
        pio.add(x);
    }

    @Override
    public List<Product> getByOrderNumber(Order o) {
        List<Product> t = new ArrayList<>();
        for (ProductsInOrder x : pio) {
            if (x.getO().getOrderNumber() == o.getOrderNumber()) {
                t.add(x.getP());
            }
        }
        return t;
    }

    @Override
    public void deleteProductFromOder(Order o, Product p) {
        for (ProductsInOrder x : pio) {
            if (x.getO().getOrderNumber() == o.getOrderNumber() && x.getP().getVendorCode() == p.getVendorCode()) {
                pio.remove(x);
                break;
            }
        }
    }
}
