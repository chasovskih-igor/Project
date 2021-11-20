package com.company.controllers;

import com.company.models.ProductsInOrder;
import com.company.repositories.OrderRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.ProductsInOrderRepository;

import java.util.List;


public class PiOController {

    public static class PiOException extends Exception {
        public PiOException(String message) {
            super(message);
        }
    }

    public static class ProductNotInOrder extends PiOException {
        public ProductNotInOrder(int s) {
            super(String.format("Товара с артикулом \"%s\" в заказе нет", s));
        }
    }

    private final ProductsInOrderRepository pioRepository;
    private final ProductRepository pRepository;
    private final OrderRepository oRepository;


    public PiOController(ProductsInOrderRepository pioRepository,ProductRepository pRepository, OrderRepository oRepository) {
        this.pioRepository = pioRepository;
        this.pRepository = pRepository;
        this.oRepository = oRepository;
    }

    public void addProductInOrder(int o, int p, int c) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {

        if (oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);
        if (pRepository.getByVendorCode(p) == null)
            throw new ProductsController.ProductDoesNotExist(p);

        pioRepository.addProductInOrder(o, p, c);
    }

    public void removeProductFromOrder(int o, int p) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist, ProductNotInOrder {
        if (oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);
        if (pRepository.getByVendorCode(p) == null)
            throw new ProductsController.ProductDoesNotExist(p);
        ProductsInOrder t = null;
        for (ProductsInOrder x : getProductsFromOrder(o)) {
            if (x.getProductVendorCode() == p) t = x;
            break;
        }
        if (t == null) throw new ProductNotInOrder(p);
        pioRepository.deleteProductFromOder(o, p);
    }

    public List<ProductsInOrder> getProductsFromOrder(int o) throws OrderController.OrderDoesNotExist {
        if (oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);

        return pioRepository.getByOrderNumber(o);
    }
}
