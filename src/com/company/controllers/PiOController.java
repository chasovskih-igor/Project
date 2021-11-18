package com.company.controllers;

import com.company.models.Order;
import com.company.models.Product;
import com.company.models.ProductsInOrder;
import com.company.repositories.ProductsInOrderRepository;

import java.util.List;


public class PiOController {
    public ProductsInOrderRepository pioRepository;

    public PiOController(ProductsInOrderRepository pioRepository) {
        this.pioRepository = pioRepository;
    }

    public void addProductInOrder(int o, int p, int c) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {

        pioRepository.addProductInOrder(o, p, c);
    }

    public void removeProductFromOrder(int o, int p) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {

        pioRepository.deleteProductFromOder(o, p);
    }

    public List<ProductsInOrder> getProductsFromOrder(int o) throws OrderController.OrderDoesNotExist {

        return pioRepository.getByOrderNumber(o);
    }
}
