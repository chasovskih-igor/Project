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

    private CustomerRepository cRepository;


    public void addNewOrder(Customer c) throws CustomerAlreadyExists {
        Customer x = cRepository.getById(c.getId());
        if (x != null) {
            throw new CustomerAlreadyExists(c.getId());
        }
        cRepository.add(c);
    }

    public List<Customer> getAll() {
        return cRepository.getAll();
    }

}
