package com.company.impl.console_ui;

import com.company.controllers.CustomerController;
import com.company.controllers.OrderController;
import com.company.controllers.PiOController;
import com.company.controllers.ProductsController;
import com.company.models.Customer;
import com.company.models.Order;
import com.company.models.Product;
import com.company.repositories.CustomerRepository;
import com.company.repositories.OrderRepository;
import com.company.repositories.ProductRepository;
import com.company.repositories.ProductsInOrderRepository;

import java.util.Scanner;

public class Application {
    private final ProductsController pController;
    private final CustomerController cController;
    private final OrderController oController;
    private final PiOController pioController;
    private final Scanner scanner;

    public Application(ProductRepository repository, OrderRepository or, CustomerRepository cr, ProductsInOrderRepository pio) {
        scanner = new Scanner(System.in);
        pController = new ProductsController(repository);
        cController = new CustomerController(cr);
        oController = new OrderController(or);
        pioController = new PiOController(pio);
    }

    private void printMainMenu() {
        System.out.println("1. Товары;\n2. Заказчики;\n3. Заказы;\n");
    }

    private void printProductMenu() {
        System.out.println("1. Добавить новый товар;\n2. Просмотреть список всех товаров;\n3. Найти товар по артикулу;\n4. Найти товар по названию модели;\n5. Изменить характеристики товара\n6. Удалить товар\n\n0.Вернуться назад ");
    }

    private void printCustomerMenu() {
        System.out.println("1. Добавить заказчика;\n2. Найти заказчика по ID;\n3. Найти заказчика по номеру телефона;\n4. Просмотреть список заказчиков;\n5. Обновить данные о заказчике;\n6. Удалить данные о закачике;\n\n0. Вернуться назад");
    }

    private void printOrderMenu() {
        System.out.println("1. Создать заказ;\n2. Найти заказ по номеру;\n3. Найти все заказы клиента;\n4. Изменить реквизиты заказа;\n5. Удалить заказ;\n6. Товары в заказах\n\n0. Вернуться назад");
    }

    private void printPiOMenu() {
        System.out.println("1. Добавить товар в заказ;\n2. Показать товары в заказе;\n3. Удалить товар из заказа;\n\n0.Вернуться назад");
    }

    private void workPiO() {
        printPiOMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1: {
                try {
                    System.out.println("Введите номер заказа: ");
                    int number = scanner.nextInt();
                    System.out.println("Введите артикул товара: ");
                    int vendorCode = scanner.nextInt();
                    System.out.println("Введите количество данного товара: ");
                    int count = scanner.nextInt();
                    pioController.addProductInOrder(oController.getByNumber(number), pController.getByVendorCode(vendorCode), count);
                } catch (OrderController.OrderDoesNotExist | ProductsController.ProductDoesNotExist orderDoesNotExist) {
                    System.out.println("Такого заказа или такого товара не существует");
                }
            }
            break;
            case 2: {
                try {
                    System.out.println("Введите номер заказа: ");
                    pioController.getProductsFromOrder(oController.getByNumber(scanner.nextInt()));
                } catch (OrderController.OrderDoesNotExist orderDoesNotExist) {
                    System.out.println(orderDoesNotExist.getMessage());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите номер заказа: ");
                    int number = scanner.nextInt();
                    System.out.println("Введите артикул товара: ");
                    int vendorCode = scanner.nextInt();
                    pioController.removeProductFromOrder(oController.getByNumber(number), pController.getByVendorCode(vendorCode));
                } catch (OrderController.OrderDoesNotExist | ProductsController.ProductDoesNotExist orderDoesNotExist) {
                    System.out.println("Такого заказа или такого товара не существует");
                }
            }
            break;
            default:
                System.out.println("Нет такой команды");
                break;
        }
    }

    private void workOrder() {
        printOrderMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1: {
                try {
                    System.out.println("Введите номер заказа: ");
                    int number = scanner.nextInt();
                    System.out.println("Введите ID заказчика: ");
                    int id = scanner.nextInt();
                    System.out.println("Введите дату заказа: ");
                    String data = scanner.nextLine();
                    System.out.println("Введите адрес доставки: ");
                    String address = scanner.nextLine();
                    System.out.println("Введите стоимость доставки: ");
                    int cost = scanner.nextInt();
                    oController.addNewOrder(new Order(number, id, data, address, cost));
                } catch (OrderController.OrderAlreadyExists orderAlreadyExists) {
                    System.out.println(orderAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                try {
                    System.out.println("Введите номер заказа: ");
                    oController.getByNumber(scanner.nextInt());
                } catch (OrderController.OrderDoesNotExist orderDoesNotExist) {
                    System.out.println(orderDoesNotExist.getMessage());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите номер заказчика: ");
                    oController.getAllByCustomerId(scanner.nextInt());
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            case 4: {
                System.out.println("Введите номер заказа: ");
                int number = scanner.nextInt();
                System.out.println("Введите ID заказчика: ");
                int id = scanner.nextInt();
                System.out.println("Введите дату заказа: ");
                String data = scanner.nextLine();
                System.out.println("Введите адрес доставки: ");
                String address = scanner.nextLine();
                System.out.println("Введите стоимость доставки: ");
                int cost = scanner.nextInt();
                oController.update(new Order(number, id, data, address, cost));
            }
            break;
            case 5: {
                try {
                    System.out.println("Введите номер заказа к удалению: ");
                    oController.removeOrder(scanner.nextInt());
                } catch (OrderController.OrderDoesNotExist orderDoesNotExist) {
                    System.out.println(orderDoesNotExist.getMessage());
                }
            }
            break;
            case 6: {
                workPiO();
            }
            break;
            default:
                System.out.println("Нет такой команды");
                break;
        }
    }

    private void workCustomer() {
        printCustomerMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1: {
                try {
                    System.out.println("Введите ID: ");
                    int id = scanner.nextInt();
                    System.out.println("Введите имя: ");
                    String name = scanner.next();
                    System.out.println("Введите фамилию: ");
                    String surname = scanner.next();
                    System.out.println("Введите отчество (если есть): ");
                    String patronymic = scanner.next();
                    System.out.println("Введите телефон: ");
                    long number = scanner.nextLong();
                    System.out.println("Введите адрес: ");
                    String address = scanner.nextLine();
                    System.out.println("Введите E-mail (если есть): ");
                    String mail = scanner.nextLine();
                    cController.addNewCustomer(new Customer(id, name, surname, patronymic, number, address, mail));
                } catch (CustomerController.CustomerAlreadyExists customerAlreadyExists) {
                    System.out.println(customerAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Введите ID искомого заказчика: ");
                try {
                    cController.getById(scanner.nextInt());
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите номер: ");
                    cController.getByPhoneNumber(scanner.nextLong());
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            case 4: {
                for (Customer c : cController.getAll()) {
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getSurname() + " " + c.getPatronymic() + " " + c.getPhoneNumber() + " " + c.getAddress() + " " + c.getE_mail());
                }
            }
            break;
            case 5: {
                System.out.println("Введите ID: ");
                int id = scanner.nextInt();
                System.out.println("Введите имя: ");
                String name = scanner.next();
                System.out.println("Введите фамилию: ");
                String surname = scanner.next();
                System.out.println("Введите отчество (если есть): ");
                String patronymic = scanner.next();
                System.out.println("Введите телефон: ");
                long number = scanner.nextLong();
                System.out.println("Введите адрес: ");
                String address = scanner.nextLine();
                System.out.println("Введите E-mail (если есть): ");
                String mail = scanner.nextLine();
                cController.update(new Customer(id, name, surname, patronymic, number, address, mail));
            }
            break;
            case 6: {
                try {
                    System.out.println("Введите ID заказчика для удаления: ");
                    Customer x = cController.getById(scanner.nextInt());
                    cController.removeCustomer(x);
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            default:
                System.out.println("Нет такой команды");
                break;
        }
    }

    private void workProduct() {
        printProductMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1: {
                try {
                    System.out.println("Введите артикул нового товара: ");
                    int vendorCode = scanner.nextInt();
                    System.out.println("Введите наличие нового товара: ");
                    boolean presence = scanner.nextBoolean();
                    System.out.println("Введите вид товара: ");
                    String technicType = scanner.next();
                    System.out.println("Введите бренд товара: ");
                    String brand = scanner.next();
                    System.out.println("Введите название модели товара");
                    String model = scanner.next();
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
                    pController.addNewProduct(new Product(vendorCode, presence, technicType, brand, model, price, weight, height, lenght, width));
                } catch (ProductsController.ProductAlreadyExists productAlreadyExists) {
                    System.out.println(productAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                for (Product p : pController.getAll()) {
                    System.out.println(p.getVendorCode() + " " + p.isPresence() + " " + p.getTechnicType() + " " + p.getBrand() + " " + p.getModel() + " " + p.getPrice() + " " + p.getWeight() + " " + p.getHeight() + " " + p.getLenght() + " " + p.getWidth());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите артикул товара: ");
                    pController.getByVendorCode(scanner.nextInt());
                } catch (ProductsController.ProductDoesNotExist productDoesNotExist) {
                    System.out.println(productDoesNotExist.getMessage());
                }
            }
            break;
            case 4: {
                try {
                    System.out.println("Введите бренд и модель товара: ");
                    pController.getByName(scanner.next(), scanner.next());
                } catch (ProductsController.ProductDoesNotExistName productDoesNotExistName) {
                    System.out.println(productDoesNotExistName.getMessage());
                }
            }
            case 5: {
                System.out.println("Введите артикул нового товара: ");
                int vendorCode = scanner.nextInt();
                System.out.println("Введите наличие нового товара: ");
                boolean presence = scanner.nextBoolean();
                System.out.println("Введите вид товара: ");
                String technicType = scanner.next();
                System.out.println("Введите бренд товара: ");
                String brand = scanner.next();
                System.out.println("Введите название модели товара");
                String model = scanner.next();
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
                pController.update(new Product(vendorCode, presence, technicType, brand, model, price, weight, height, lenght, width));
            }
            break;
            case 6: {
                try {
                    System.out.println("Введите артикул товара к удалению: ");
                    Product p = pController.getByVendorCode(scanner.nextInt());
                    pController.removeProduct(p);
                } catch (ProductsController.ProductDoesNotExist productDoesNotExist) {
                    System.out.println(productDoesNotExist.getMessage());
                }
            }
            break;
            default: {
                System.out.println("Нет такой команды.");
            }
            break;
        }
    }

    public boolean command() {
        printMainMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                workProduct();
            }
            break;
            case 2: {
                workCustomer();
            }
            break;
            case 3: {
                workOrder();
            }
            break;
            default:
                return false;
        }
        return true;
    }
}
