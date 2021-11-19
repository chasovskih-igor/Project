package com.company.controllers;

import com.company.models.ProductsInOrder;
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

    public ProductsInOrderRepository pioRepository;

    public PiOController(ProductsInOrderRepository pioRepository) {
        this.pioRepository = pioRepository;
    }

    public void addProductInOrder(int o, int p, int c) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist {

        if (OrderController.oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);
        if (ProductsController.pRepository.getByVendorCode(p) == null)
            throw new ProductsController.ProductDoesNotExist(p);

        pioRepository.addProductInOrder(o, p, c);
    }

    public void removeProductFromOrder(int o, int p) throws OrderController.OrderDoesNotExist, ProductsController.ProductDoesNotExist, ProductNotInOrder {
        if (OrderController.oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);
        if (ProductsController.pRepository.getByVendorCode(p) == null)
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
        if (OrderController.oRepository.getByNumber(o) == null) throw new OrderController.OrderDoesNotExist(o);

        return pioRepository.getByOrderNumber(o);
    }
}
