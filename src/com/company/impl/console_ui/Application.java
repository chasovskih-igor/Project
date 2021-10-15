package com.company.impl.console_ui;

import com.company.controllers.ProductsController;
import com.company.impl.repositories.arr_impl.PiORepositoryArrayImpl;
import com.company.models.Product;
import com.company.repositories.CustomerRepository;
import com.company.repositories.OrderRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.ProductsInOrderRepository;

import java.util.Scanner;

public class Application {
    private final OrderRepository or;
    private final CustomerRepository cr;
    private final ProductsInOrderRepository pio;
    private final ProductRepository repository;
    private final ProductsController pController;
    private final Scanner scanner;

    public Application(ProductRepository repository, OrderRepository or, CustomerRepository cr, ProductsInOrderRepository pio) {
        this.repository = repository;
        this.or = or;
        this.cr = cr;
        this.pio = pio;
        scanner = new Scanner(System.in);
        pController = new ProductsController(repository);
    }
    private void printMainMenu() {
        System.out.println("1. Товары");
    }
    private void printProductMenu() {
        System.out.println("1. Добавить новый товар;\n2. Просмотреть список всех товаров;\n3. Найти товар по артикулу;\n4.Найти товар по названию;\n\n0.Вернуться назад ");
    }
    private  void workProduct() throws ProductsController.ProductAlreadyExists {
        printProductMenu();
        System.out.println("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0: break;
            case 1: {
                try{
                    System.out.println("Введите артикул нового товара: ");
                    int vendorCode = scanner.nextInt();
                    System.out.println("Введите наличие нового товара: ");
                    boolean presence = scanner.nextBoolean();
                    System.out.println("Введите вид товара: ");
                    String technicType = scanner.next();
                    System.out.println("Введите цену товара: ");
                    int price = scanner.nextInt();
                    System.out.println("Введите вес товара: ");
                    int weight = scanner.nextInt();
                    System.out.println("Введите высоту товара: ");
                    int height = scanner.nextInt();
                    System.out.println("Введите длину товара: ");
                    int lenght = scanner.nextInt();
                    System.out.println("Введите ширину товара: ");
                    int width = scanner.nextInt();
                    System.out.println("Введите описание товара: ");
                    String description = scanner.next();
                    pController.addNewProduct(new Product(vendorCode, presence, technicType, price, weight, height, lenght, width, description));
                } catch (ProductsController.ProductAlreadyExists productAlreadyExists) {System.out.println(productAlreadyExists.getMessage());}
            } break;
            case 2: {
                for (Product p: pController.getAll()) {
                    System.out.println(p.getVendorCode() + " " + p.getModel());
                }
            } break;
            default: {
                System.out.println("Нет такой команды.");
            }

        }
    }
    public boolean command() throws ProductsController.ProductAlreadyExists {
        printMainMenu();
        System.out.println("> ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: {
                workProduct();
            }break;
            default: return false;
        }
    return true;}
}
