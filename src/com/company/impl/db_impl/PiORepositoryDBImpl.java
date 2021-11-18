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
    private static final String TABLE_NAME = "ProductsInOrders";
    private static final String PRODUCT_COLUMN_NAME = "product";
    private static final String ORDER_COLUMN_NAME = "custom";
    private static final String COUNT_COLUMN_NAME = "countt";

    private final DBConnectionProvider dbProvider;

    public PiORepositoryDBImpl(DBConnectionProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    @Override
    public List<ProductsInOrder> getByOrderNumber(int o) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, ORDER_COLUMN_NAME);
        List<ProductsInOrder> pio = new ArrayList<>();
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, o);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int product = rslt.getInt(PRODUCT_COLUMN_NAME);
                int custom = rslt.getInt(ORDER_COLUMN_NAME);
                int countt = rslt.getInt(COUNT_COLUMN_NAME);
                pio.add(new ProductsInOrder(custom, product, countt));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pio;
    }

    @Override
    public void addProductInOrder(int o, int p, int c) {
        final String query = String.format("INSERT INTO %s VALUES(?, ?, ?);", TABLE_NAME);
        try (Connection cn = dbProvider.getConnection()) {
            PreparedStatement q = cn.prepareStatement(query);
            q.setInt(1, o);
            q.setInt(2, p);
            q.setInt(3, c);
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteProductFromOder(int o, int p) {
        final String query = String.format("DELETE FROM %s WHERE %s= ? AND %s= ?;", TABLE_NAME, ORDER_COLUMN_NAME, PRODUCT_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, o);
            q.setInt(2, p);
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
