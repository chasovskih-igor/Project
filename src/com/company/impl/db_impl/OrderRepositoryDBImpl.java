package com.company.impl.db_impl;

import com.company.SQLSettings.DBConnectionProvider;
import com.company.models.Order;
import com.company.repositories.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryDBImpl implements OrderRepository {
    private static final String TABLE_NAME = "Orders";
    private static final String NUMBER_COLUMN_NAME = "orderNumber";
    private static final String CUSTOMER_ID_COLUMN_NAME = "customerId";
    private static final String DATA_COLUMN_NAME = "data";
    private static final String DELIVERY_ADDRESS_COLUMN_NAME = "deliveryAddress";
    private static final String DELIVERY_COST_COLUMN_NAME = "deliveryCost";
    private static final String DELIVERY_TYPE_COLUMN_NAME = "delivery";
    private static final String PAYMENT_TYPE_COLUMN_NAME = "payment";

    private final DBConnectionProvider dbProvider;

    public OrderRepositoryDBImpl(DBConnectionProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    @Override
    public List<Order> getAll() {
        final String query = String.format("SELECT * FROM %s;", TABLE_NAME);
        List<Order> orders = new ArrayList<>();
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int number = rslt.getInt(NUMBER_COLUMN_NAME);
                int customerId = rslt.getInt(CUSTOMER_ID_COLUMN_NAME);
                String data = rslt.getString(DATA_COLUMN_NAME);
                String deliveryAddress = rslt.getString(DELIVERY_ADDRESS_COLUMN_NAME);
                int deliveryCost = rslt.getInt(DELIVERY_COST_COLUMN_NAME);
                String delivery = rslt.getString(DELIVERY_TYPE_COLUMN_NAME);
                String payment = rslt.getString(PAYMENT_TYPE_COLUMN_NAME);
                orders.add(new Order(number, customerId, data, deliveryAddress, deliveryCost, delivery, payment));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getByNumber(int reqOrderNumber) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, NUMBER_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, reqOrderNumber);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int number = rslt.getInt(NUMBER_COLUMN_NAME);
                int customerId = rslt.getInt(CUSTOMER_ID_COLUMN_NAME);
                String data = rslt.getString(DATA_COLUMN_NAME);
                String deliveryAddress = rslt.getString(DELIVERY_ADDRESS_COLUMN_NAME);
                int deliveryCost = rslt.getInt(DELIVERY_COST_COLUMN_NAME);
                String delivery = rslt.getString(DELIVERY_TYPE_COLUMN_NAME);
                String payment = rslt.getString(PAYMENT_TYPE_COLUMN_NAME);
                return new Order(number, customerId, data, deliveryAddress, deliveryCost, delivery, payment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Order o) {
        final String query = String.format("INSERT INTO %s VALUES(null,?,?,?,?,?,?);", TABLE_NAME);
        try (Connection cn = dbProvider.getConnection()) {
            PreparedStatement q = cn.prepareStatement(query);
            q.setInt(1, o.getCustomerId());
            q.setString(2, o.getData());
            q.setString(3, o.getDeliveryAddress());
            q.setInt(4, o.getDeliveryCost());
            q.setString(5, o.getDelivery());
            q.setString(6, o.getPayment());
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Order o) {
        final String query = String.format("UPDATE %s SET %s =?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?;", TABLE_NAME, CUSTOMER_ID_COLUMN_NAME, DATA_COLUMN_NAME, DELIVERY_ADDRESS_COLUMN_NAME, DELIVERY_COST_COLUMN_NAME, DELIVERY_TYPE_COLUMN_NAME, PAYMENT_TYPE_COLUMN_NAME, NUMBER_COLUMN_NAME);
        try (Connection cn = dbProvider.getConnection()) {
            PreparedStatement q = cn.prepareStatement(query);
            q.setInt(1, o.getCustomerId());
            q.setString(2, o.getData());
            q.setString(3, o.getDeliveryAddress());
            q.setInt(4, o.getDeliveryCost());
            q.setString(5, o.getDelivery());
            q.setString(6, o.getPayment());
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Order o) {
        final String query = String.format("DELETE FROM %s WHERE %s=?;", TABLE_NAME, NUMBER_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, o.getOrderNumber());
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllByCustomerId(int reqCustomerId) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, CUSTOMER_ID_COLUMN_NAME);
        List<Order> orders = new ArrayList<>();
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, reqCustomerId);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int number = rslt.getInt(NUMBER_COLUMN_NAME);
                int customerId = rslt.getInt(CUSTOMER_ID_COLUMN_NAME);
                String data = rslt.getString(DATA_COLUMN_NAME);
                String deliveryAddress = rslt.getString(DELIVERY_ADDRESS_COLUMN_NAME);
                int deliveryCost = rslt.getInt(DELIVERY_COST_COLUMN_NAME);
                String delivery = rslt.getString(DELIVERY_TYPE_COLUMN_NAME);
                String payment = rslt.getString(PAYMENT_TYPE_COLUMN_NAME);
                orders.add(new Order(number, customerId, data, deliveryAddress, deliveryCost, delivery, payment));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }


    @Override
    public void copy(Order o, Order k) {

    }
}
