package com.company.impl.repositories.arr_impl;

import com.company.models.Product;
import com.company.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryArrayImpl implements ProductRepository {

    private static ProductsRepositoryArrayImpl instance = null;

    public static ProductsRepositoryArrayImpl getInstance() {
        if (instance == null)
            instance = new ProductsRepositoryArrayImpl();
        return instance;
    }

    private final List<Product> products = new ArrayList<>();

    @Override
    public void copy(Product p, Product k) {
        p.setBrand(k.getBrand());
        p.setModel(k.getModel());
        p.setPresence(k.isPresence());
        p.setPrice(k.getPrice());
        p.setTechnicType(k.getTechnicType());
        p.setVendorCode(k.getVendorCode());
    }

    @Override
    public List<Product> getAll() {
        return products;
    } //Возвращает список товаров

    @Override
    public Product getByVendorCode(int vendorCode) { //Находит товар по вендоркоду
        for (Product p : products)
            if (p.getVendorCode() == vendorCode)
                return p;
        return null;
    }

    @Override
    public Product getByName(String brand, String model) { //Находит товар по названию модели
        for (Product p : products)
            if (p.getModel().equals(model) && p.getBrand().equals(brand))
                return p;
        return null;
    }

    @Override
    public void add(Product p) {
        products.add(p);
    } //Добавляет новый товар

    @Override
    public void update(Product p) { // Обновляет товар
        for (Product x : products)
            if (p.getVendorCode() == x.getVendorCode()){
                copy(x, p);
                break;
            } else products.add(p);
    }

    @Override
    public void delete(Product p) { // Удаляет товар
        products.remove(p);
    }

}
