package com.company.repositories;

import com.company.models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();

    Customer getById(int id);

    Customer getByPhoneNumber(long phoneNumber);

    void add(Customer c);

    void update(Customer c);

    void deleteById(int id);

    void copy(Customer c, Customer k);
}
