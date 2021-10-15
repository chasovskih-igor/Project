package com.company.repositories;

import com.company.models.Product;

import java.util.List;

public interface ProductRepository {


    List<Product> getAll();

    Product getByVendorCode(int vendorCode);

    Product getByModel(String model);

    void add(Product p);

    void update(Product p);

    void delete(Product p);

    void copy(Product p, Product k);
}
