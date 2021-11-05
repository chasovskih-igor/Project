package com.company;

import com.company.MySQLSettings.DBConnectionProvider;
import com.company.MySQLSettings.MySQLDBProvider;
import com.company.impl.console_ui.Application;
import com.company.impl.db_impl.ProductsRepositoryDBImpl;
import com.company.impl.repositories.arr_impl.CustomerRepositoryArrayImpl;
import com.company.impl.repositories.arr_impl.OrderRepositoryArrayImpl;
import com.company.impl.repositories.arr_impl.PiORepositoryArrayImpl;
import com.company.impl.repositories.arr_impl.ProductsRepositoryArrayImpl;
import com.company.repositories.CustomerRepository;
import com.company.repositories.OrderRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.ProductsInOrderRepository;

public class Main {

    public static void main(String[] args) {
        DBConnectionProvider db = new MySQLDBProvider("");
        ProductRepository pr = new ProductsRepositoryDBImpl(db);
        OrderRepository or = OrderRepositoryArrayImpl.getInstance();
        CustomerRepository cr = CustomerRepositoryArrayImpl.getInstance();
        ProductsInOrderRepository pio = PiORepositoryArrayImpl.getInstance();
        Application app = new Application(pr, or, cr, pio);
        while (app.command());
    }
}
