package com.company.controllers;

import com.company.models.Order;
import com.company.models.Product;
import com.company.repositories.ProductsInOrderRepository;

public class PiOController {
    public ProductsInOrderRepository pioRepository;

    public PiOController(ProductsInOrderRepository pioRepository) {
        this.pioRepository = pioRepository;
    }

    public void addProductInOrder(Order o, Product p, int c) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {
        if (o == null) {
            assert false;
            throw new OrderController.OrderDoesNotExist(o.getOrderNumber());
        }
        if (p == null) {
            assert false;
            throw new ProductsController.ProductDoesNotExist(p.getVendorCode());
        }
        pioRepository.addProductInOrder(o, p, c);
    }

    public void removeProductFromOrder(Order o, Product p) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {
        if (o == null) {
            assert false;
            throw new OrderController.OrderDoesNotExist(o.getOrderNumber());
        }
        if (p == null) {
            assert false;
            throw new ProductsController.ProductDoesNotExist(p.getVendorCode());
        }
        pioRepository.deleteProductFromOder(o, p);
    }

    public void getProductsFromOrder(Order o) throws OrderController.OrderDoesNotExist {
        if (o == null) {
            assert false;
            throw new OrderController.OrderDoesNotExist(o.getOrderNumber());
        }
        pioRepository.getByOrderNumber(o);
    }
}
