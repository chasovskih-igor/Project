package com.company.impl.db_impl;

import com.company.SQLSettings.DBConnectionProvider;
import com.company.models.Order;
import com.company.models.Product;
import com.company.models.ProductsInOrder;
import com.company.repositories.ProductsInOrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiORepositoryDBImpl implements ProductsInOrderRepository {
    private static final String TABLE_NAME = "ProductsInOrder";
    private static final String PRODUCT_COLUMN_NAME = "product";
    private static final String ORDER_COLUMN_NAME = "custom";
    private static final String COUNT_COLUMN_NAME = "countt";

    private final DBConnectionProvider dbProvider;

    public PiORepositoryDBImpl(DBConnectionProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    @Override
    public List<Product> getByOrderNumber(Order o) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, ORDER_COLUMN_NAME);
        List<Product> pio = new ArrayList<>();
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, o.getOrderNumber());
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int product = rslt.getInt(PRODUCT_COLUMN_NAME);
                int custom = rslt.getInt(ORDER_COLUMN_NAME);
                int countt = rslt.getInt(COUNT_COLUMN_NAME);
                //pio.add(new ProductsInOrder(product, custom, countt));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pio;
    }

    @Override
    public void addProductInOrder(Order o, Product p, int c) {

    }

    @Override
    public void deleteProductFromOder(Order o, Product p) {

    }
}
