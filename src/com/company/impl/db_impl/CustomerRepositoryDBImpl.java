package com.company.impl.db_impl;

import com.company.SQLSettings.DBConnectionProvider;
import com.company.models.Customer;
import com.company.repositories.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryDBImpl implements CustomerRepository {
    private static final String TABLE_NAME = "Customers";
    private static final String ID_COLUMN_NAME = "id";
    private static final String PHONENUMBER_COLUMN_NAME = "phoneNumber";
    private static final String SURNAME_COLUMN_NAME = "surname";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String PATRONYMIC_COLUMN_NAME = "patronymic";
    private static final String ADDRESS_COLUMN_NAME = "address";
    private static final String E_MAIL_COLUMN_NAME = "e_mail";
    private final DBConnectionProvider dbProvider;

    public CustomerRepositoryDBImpl(DBConnectionProvider dbProvider) {
        this.dbProvider = dbProvider;
    }

    @Override
    public List<Customer> getAll() {
        final String query = String.format("SELECT * FROM %s;", TABLE_NAME);
        List<Customer> customers = new ArrayList<>();
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt(ID_COLUMN_NAME);
                long phoneNumber = rslt.getLong(PHONENUMBER_COLUMN_NAME);
                String surname = rslt.getString(SURNAME_COLUMN_NAME);
                String name = rslt.getString(NAME_COLUMN_NAME);
                String patronymic = rslt.getString(PATRONYMIC_COLUMN_NAME);
                String address = rslt.getString(ADDRESS_COLUMN_NAME);
                String e_mail = rslt.getString(E_MAIL_COLUMN_NAME);
                customers.add(new Customer(id, name, surname, patronymic, phoneNumber, address, e_mail));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getById(int reqId) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, ID_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, reqId);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt(ID_COLUMN_NAME);
                long phoneNumber = rslt.getLong(PHONENUMBER_COLUMN_NAME);
                String surname = rslt.getString(SURNAME_COLUMN_NAME);
                String name = rslt.getString(NAME_COLUMN_NAME);
                String patronymic = rslt.getString(PATRONYMIC_COLUMN_NAME);
                String address = rslt.getString(ADDRESS_COLUMN_NAME);
                String e_mail = rslt.getString(E_MAIL_COLUMN_NAME);
                return new Customer( id, name, surname, patronymic, phoneNumber, address, e_mail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getByPhoneNumber(long reqPhoneNumber) {
        final String query = String.format("SELECT * FROM %s WHERE %s == ?;", TABLE_NAME, PHONENUMBER_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setLong(1, reqPhoneNumber);
            ResultSet rslt = q.executeQuery();
            while (rslt.next()) {
                int id = rslt.getInt(ID_COLUMN_NAME);
                long phoneNumber = rslt.getLong(PHONENUMBER_COLUMN_NAME);
                String surname = rslt.getString(SURNAME_COLUMN_NAME);
                String name = rslt.getString(NAME_COLUMN_NAME);
                String patronymic = rslt.getString(PATRONYMIC_COLUMN_NAME);
                String address = rslt.getString(ADDRESS_COLUMN_NAME);
                String e_mail = rslt.getString(E_MAIL_COLUMN_NAME);
                return new Customer(id, name, surname, patronymic, phoneNumber, address, e_mail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Customer c) {
        final String query = String.format("INSERT INTO %s VALUES(null,?,?,?,?,?,?);", TABLE_NAME);
        try (Connection cn = dbProvider.getConnection()) {
            PreparedStatement q = cn.prepareStatement(query);
            q.setString(1, c.getName());
            q.setString(2, c.getSurname());
            q.setString(3, c.getPatronymic());
            q.setLong(4, c.getPhoneNumber());
            q.setString(5, c.getAddress());
            q.setString(6, c.getE_mail());
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Customer c) {
        final String query = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?;", TABLE_NAME, NAME_COLUMN_NAME, SURNAME_COLUMN_NAME, PATRONYMIC_COLUMN_NAME, PHONENUMBER_COLUMN_NAME, ADDRESS_COLUMN_NAME, E_MAIL_COLUMN_NAME, ID_COLUMN_NAME);
        try (Connection cn = dbProvider.getConnection()) {
            PreparedStatement q = cn.prepareStatement(query);
            q.setString(1, c.getName());
            q.setString(2, c.getSurname());
            q.setString(3, c.getPatronymic());
            q.setLong(4, c.getPhoneNumber());
            q.setString(5, c.getAddress());
            q.setString(6, c.getE_mail());
            q.setInt(7,c.getId());
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        final String query = String.format("DELETE FROM %s WHERE %s=?;", TABLE_NAME, ID_COLUMN_NAME);
        try (Connection c = dbProvider.getConnection()) {
            PreparedStatement q = c.prepareStatement(query);
            q.setInt(1, id);
            q.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void copy(Customer c, Customer k) {

    }
}
