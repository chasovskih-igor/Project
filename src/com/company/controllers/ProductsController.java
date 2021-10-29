package com.company.controllers;

import com.company.models.Product;
import com.company.repositories.ProductRepository;

import java.util.List;

public class ProductsController {
    public static class ProductsException extends Exception {
        public ProductsException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyExists extends ProductsException {
        public ProductAlreadyExists(String s, String cause) {
            super(String.format("Товар \"%s\" уже существует", s, "Причина - название и/или артикул: \"%s\"", cause));
        }
    }

    public static class ProductDoesNotExist extends ProductsException {
        public ProductDoesNotExist(int s) {
            super(String.format("Товара с артикулом \"%s\" не существует", s));
        }
    }
    public static class ProductDoesNotExistName extends ProductsException {
        public ProductDoesNotExistName(String s) {
            super(String.format("Товара с названием \"%s\" не существует", s));
        }
    }

    private final ProductRepository pRepository;

    public ProductsController(ProductRepository pRepository) {
        this.pRepository = pRepository;
    }

    public void addNewProduct(Product p) throws ProductAlreadyExists {
        Product x = pRepository.getByName(p.getBrand(), p.getModel());
        if (x != null) {
            throw new ProductAlreadyExists(p.getBrand() + " " + p.getModel(), p.getBrand() + " " + p.getModel());
        }
        x = pRepository.getByVendorCode(p.getVendorCode());
        if (x != null) {
            throw new ProductAlreadyExists(p.getModel(), Integer.toString(p.getVendorCode()));
        }
        pRepository.add(p);
    }

    public void update(Product p) {
        pRepository.update(p);
    }

    public void removeProduct(Product p) throws ProductDoesNotExist {
        Product x = pRepository.getByVendorCode(p.getVendorCode());
        if (x == null) {
            throw new ProductDoesNotExist(p.getVendorCode());
        }
        pRepository.delete(p);
        System.out.println("\nТовар успешно удалён");
    }

    public List<Product> getAll() {
        return pRepository.getAll();
    }

    public Product getByVendorCode(int v) throws ProductDoesNotExist {
        Product x = pRepository.getByVendorCode(v);
        if (x == null) {
            throw new ProductDoesNotExist(v);
        }
        return pRepository.getByVendorCode(v);
    }
    public Product getByName(String b, String m) throws ProductDoesNotExistName {
        Product x = pRepository.getByName(b, m);
        if (x == null) {
            throw new ProductDoesNotExistName(b + " " + m);
        }
        return pRepository.getByName(b, m);
    }
}
