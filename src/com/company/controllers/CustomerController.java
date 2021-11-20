package com.company.controllers;

import com.company.models.Customer;
import com.company.repositories.CustomerRepository;

import java.util.List;

public class CustomerController {

    public static class CustomerException extends Exception {
        public CustomerException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyExists extends CustomerException {
        public CustomerAlreadyExists(int s) {
            super(String.format("Заказчик с id \"%s\" уже существует", s));
        }
    }

    public static class CustomerDoesNotExists extends CustomerException {
        public CustomerDoesNotExists() {
            super("Такого заказчика не существует");
        }
    }

    private final CustomerRepository cRepository;

    public CustomerController(CustomerRepository cRepository) {
        this.cRepository = cRepository;
    }

    public void addNewCustomer(Customer c) throws CustomerAlreadyExists {
        Customer x = cRepository.getById(c.getId());
        if (x != null) {
            throw new CustomerAlreadyExists(c.getId());
        }
        cRepository.add(c);
    }

    public Customer getById(int id) throws CustomerDoesNotExists {
        Customer x = cRepository.getById(id);
        if (x == null) {
            throw new CustomerDoesNotExists();
        }
        return x;
    }

    public Customer getByPhoneNumber(long pn) throws CustomerDoesNotExists {
        Customer x = cRepository.getByPhoneNumber(pn);
        if (x == null) {
            throw new CustomerDoesNotExists();
        }
        return x;
    }

    public void update(Customer c) {
        cRepository.update(c);
    }

    public void removeCustomer(Customer c) throws CustomerDoesNotExists {
        Customer x = cRepository.getById(c.getId());
        if (x == null) throw new CustomerDoesNotExists();
        cRepository.deleteById(c.getId());
    }

    public List<Customer> getAll() {
        return cRepository.getAll();
    }

}
