package com.company.impl.console_ui;

import com.company.controllers.CustomerController;
import com.company.controllers.OrderController;
import com.company.controllers.PiOController;
import com.company.controllers.ProductsController;
import com.company.models.*;
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
                    pioController.addProductInOrder(number, vendorCode, count);
                } catch (OrderController.OrderDoesNotExist | ProductsController.ProductDoesNotExist orderDoesNotExist) {
                    System.out.println("Такого заказа или такого товара не существует");
                }
            }
            break;
            case 2: {
                try {
                    System.out.println("Введите номер заказа: ");
                    for (ProductsInOrder x: pioController.getProductsFromOrder(scanner.nextInt())) System.out.println(x.getOrderNumber() + " | " + x.getProductVendorCode() + " | " + x.getCount());
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
                    pioController.removeProductFromOrder(number, vendorCode);
                    System.out.println("Товар успешно удалён");
                } catch (OrderController.OrderDoesNotExist | ProductsController.ProductDoesNotExist | PiOController.ProductNotInOrder orderDoesNotExist) {
                    System.out.println(orderDoesNotExist.getMessage());
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
                    System.out.println("Введите ID заказчика: ");
                    int id = scanner.nextInt();
                    System.out.println("Введите дату заказа: ");
                    String data = scanner.next();
                    System.out.println("Введите адрес доставки: ");
                    String address = scanner.next();
                    System.out.println("Введите стоимость доставки: ");
                    int cost = scanner.nextInt();
                    System.out.println("Выберите тип доставки:\n1. Доставка к дому\n2. Самовывоз");
                    DeliveryType delivery;
                    if (scanner.nextInt() == 1)  delivery = DeliveryType.DeliveryToHouse;
                    else delivery = DeliveryType.SelfPickUp;
                    System.out.println("Выберите тип оплаты:\n1. По карте\n2. Наличными");
                    PaymentType payment;
                    if (scanner.nextInt() == 1) payment = PaymentType.ByCard;
                    else payment = PaymentType.Cash;
                    oController.addNewOrder(new Order(id, data, address, cost, delivery, payment));
                } catch (OrderController.OrderAlreadyExists orderAlreadyExists) {
                    System.out.println(orderAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                try {
                    System.out.println("Введите номер заказа: ");
                    Order o = oController.getByNumber(scanner.nextInt());
                    System.out.println(o.getOrderNumber() + " | " + o.getCustomerId() + " | " + o.getData() + " | " + o.getDeliveryAddress() + " | " + o.getDeliveryCost() + " | " + o.getDelivery() + " | " + o.getPayment());
                } catch (OrderController.OrderDoesNotExist orderDoesNotExist) {
                    System.out.println(orderDoesNotExist.getMessage());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите номер заказчика: ");
                    for (Order x : oController.getAllByCustomerId(scanner.nextInt())) System.out.println(x.getOrderNumber() + " | " + x.getCustomerId() + " | " + x.getData() + " | " + x.getDeliveryAddress() + " | " + x.getDeliveryCost() + " | " + x.getDelivery() + " | " + x.getPayment());
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
                System.out.println("Выберите тип доставки:\n1. Доставка к дому;\n2. Самовывоз\n");
                DeliveryType delivery;
                if (scanner.nextInt() == 1)  delivery = DeliveryType.DeliveryToHouse;
                else delivery = DeliveryType.SelfPickUp;
                System.out.println("Выберите тип оплаты:\n1. По карте;\n2. Наличными\n");
                PaymentType payment;
                if (scanner.nextInt() == 1) payment = PaymentType.ByCard;
                else payment = PaymentType.Cash;
                oController.update(new Order(number, id, data, address, cost, delivery, payment));
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
                    System.out.println("Введите имя: ");
                    String name = scanner.next();
                    System.out.println("Введите фамилию: ");
                    String surname = scanner.next();
                    System.out.println("Введите отчество (если есть): ");
                    String patronymic = scanner.next();
                    System.out.println("Введите телефон: ");
                    long number = scanner.nextLong();
                    System.out.println("Введите адрес: ");
                    String address = scanner.next();
                    System.out.println("Введите E-mail (если есть): ");
                    String mail = scanner.next();
                    cController.addNewCustomer(new Customer( name, surname, patronymic, number, address, mail));
                } catch (CustomerController.CustomerAlreadyExists customerAlreadyExists) {
                    System.out.println(customerAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Введите ID искомого заказчика: ");
                try {
                    Customer c = cController.getById(scanner.nextInt());
                    System.out.println(c.getId() + " | " + c.getName() + " | " + c.getSurname() + " | " + c.getPatronymic() + " | " + c.getPhoneNumber() + " | " + c.getAddress() + " | " + c.getE_mail());
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите номер: ");
                    Customer c = cController.getByPhoneNumber(scanner.nextLong());
                    System.out.println(c.getId() + " | " + c.getName() + " | " + c.getSurname() + " | " + c.getPatronymic() + " | " + c.getPhoneNumber() + " | " + c.getAddress() + " | " + c.getE_mail());
                } catch (CustomerController.CustomerDoesNotExists customerDoesNotExists) {
                    System.out.println(customerDoesNotExists.getMessage());
                }
            }
            break;
            case 4: {
                for (Customer c : cController.getAll()) {
                    System.out.println(c.getId() + " | " + c.getName() + " | " + c.getSurname() + " | " + c.getPatronymic() + " | " + c.getPhoneNumber() + " | " + c.getAddress() + " | " + c.getE_mail());
                }
            }
            break;
            case 5: {
                System.out.println("Введите id: ");
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
                String address = scanner.next();
                System.out.println("Введите E-mail (если есть): ");
                String mail = scanner.next();
                cController.update(new Customer(id ,name, surname, patronymic, number, address, mail));
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
                    String presence = scanner.next();
                    System.out.println("Введите вид товара: ");
                    String technicType = scanner.next();
                    System.out.println("Введите бренд товара: ");
                    String brand = scanner.next();
                    System.out.println("Введите название модели товара");
                    String model = scanner.next();
                    System.out.println("Введите цену товара: ");
                    int price = scanner.nextInt();
                    pController.addNewProduct(new Product(vendorCode, presence.equals("+"), technicType, brand, model, price));
                } catch (ProductsController.ProductAlreadyExists productAlreadyExists) {
                    System.out.println(productAlreadyExists.getMessage());
                }
            }
            break;
            case 2: {
                for (Product p : pController.getAll()) {
                    System.out.println(p.getVendorCode() + " | " + p.isPresence() + " | " + p.getTechnicType() + " | " + p.getBrand() + " | " + p.getModel() + " | " + p.getPrice());
                }
            }
            break;
            case 3: {
                try {
                    System.out.println("Введите артикул товара: ");
                    Product p = pController.getByVendorCode(scanner.nextInt());
                    System.out.println(p.getVendorCode() + " | " + p.isPresence() + " | " + p.getTechnicType() + " | " + p.getBrand() + " | " + p.getModel() + " | " + p.getPrice());
                } catch (ProductsController.ProductDoesNotExist productDoesNotExist) {
                    System.out.println(productDoesNotExist.getMessage());
                }
            }
            break;
            case 4: {
                try {
                    System.out.println("Введите бренд и модель товара: ");
                    Product p = pController.getByName(scanner.next(), scanner.next());
                    System.out.println(p.getVendorCode() + " | " + p.isPresence() + " | " + p.getTechnicType() + " | " + p.getBrand() + " | " + p.getModel() + " | " + p.getPrice());
                } catch (ProductsController.ProductDoesNotExistName productDoesNotExistName) {
                    System.out.println(productDoesNotExistName.getMessage());
                }
            }
            case 5: {
                System.out.println("Введите артикул товара: ");
                int vendorCode = scanner.nextInt();
                System.out.println("Введите наличие товара (+ или -): ");
                String presence = scanner.next();
                System.out.println("Введите вид товара: ");
                String technicType = scanner.next();
                System.out.println("Введите бренд товара: ");
                String brand = scanner.next();
                System.out.println("Введите название модели товара");
                String model = scanner.next();
                System.out.println("Введите цену товара: ");
                int price = scanner.nextInt();
                pController.update(new Product(vendorCode, presence.equals("+"), technicType, brand, model, price));
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
