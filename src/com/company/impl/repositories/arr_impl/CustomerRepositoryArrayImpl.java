package com.company.impl.repositories.arr_impl;

import com.company.models.Customer;
import com.company.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryArrayImpl implements CustomerRepository {

    private static CustomerRepositoryArrayImpl instance = null;

    public static CustomerRepositoryArrayImpl getInstance() {
        if (instance == null)
            instance = new CustomerRepositoryArrayImpl();
        return instance;
    }

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void copy(Customer c, Customer k) {
        c.setAddress(k.getAddress());
        c.setE_mail(k.getE_mail());
        c.setId(k.getId());
        c.setName(k.getName());
        c.setPatronymic(k.getPatronymic());
        c.setPhoneNumber(k.getPhoneNumber());
        c.setSurname(k.getSurname());
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public Customer getById(int id) {
        for (Customer c : customers)
            if (c.getId() == id)
                return c;
        return null;
    }

    @Override
    public Customer getByPhoneNumber(long phoneNumber) {
        for (Customer c : customers)
            if (c.getPhoneNumber() == phoneNumber)
                return c;
        return null;
    }

    @Override
    public void add(Customer c) {
        customers.add(c);
    }

    @Override
    public void update(Customer c) {
        for (Customer x : customers)
            if (x.getId() == c.getId()) {
                copy(x, c);
                break;
            } else customers.add(c);
    }

    @Override
    public void deleteById(int id) {
        for (Customer x : customers)
            if (x.getId() == id) {
                customers.remove(x);
                break;
            }
    }
}
