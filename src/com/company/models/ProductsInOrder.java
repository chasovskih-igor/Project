package com.company.models;

public class ProductsInOrder {
    private int productVendorCode;
    private int orderNumber;
    private int count;

    public ProductsInOrder(int p, int o, int count) {
        this.productVendorCode = p;
        this.orderNumber = o;
        this.count = count;
    }

    public int getProductVendorCode() {
        return productVendorCode;
    }

    public void setProductVendorCode(int productVendorCode) {
        this.productVendorCode = productVendorCode;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
