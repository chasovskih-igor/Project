package com.company;

import com.company.SQLSettings.DBConnectionProvider;
import com.company.SQLSettings.SQLDBProvider;
import com.company.impl.console_ui.Application;
import com.company.impl.db_impl.CustomerRepositoryDBImpl;
import com.company.impl.db_impl.OrderRepositoryDBImpl;
import com.company.impl.db_impl.PiORepositoryDBImpl;
import com.company.impl.db_impl.ProductsRepositoryDBImpl;
import com.company.impl.repositories.arr_impl.OrderRepositoryArrayImpl;
import com.company.impl.repositories.arr_impl.PiORepositoryArrayImpl;
import com.company.repositories.CustomerRepository;
import com.company.repositories.OrderRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.ProductsInOrderRepository;

public class Main {

    public static void main(String[] args) {
        DBConnectionProvider db = new SQLDBProvider("D:\\sqlite\\internetshop.db");
        ProductRepository pr = new ProductsRepositoryDBImpl(db);
        OrderRepository or = new OrderRepositoryDBImpl(db);
        CustomerRepository cr = new CustomerRepositoryDBImpl(db);
        ProductsInOrderRepository pio = new PiORepositoryDBImpl(db);
        Application app = new Application(pr, or, cr, pio);
        while (app.command());
    }
}
