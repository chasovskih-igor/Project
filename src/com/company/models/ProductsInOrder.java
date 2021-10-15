package com.company.models;

public class ProductsInOrder {
    private Product p;
    private Order o;
    private int count;

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
