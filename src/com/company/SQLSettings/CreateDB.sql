CREATE TABLE Products(
    vendorCode INTEGER PRIMARY KEY NOT NULL,
    presence INTEGER NOT NULL DEFAULT 1,
    technicType STRING NOT NULL,
    brand STRING NOT NULL,
    model STRING NOT NULL,
    price INTEGER NOT NULL,
    weight INTEGER NOT NULL,
    height INTEGER NOT NULL,
    lenght INTEGER NOT NULL,
    width INTEGER NOT NULL
);
CREATE TABLE Customers(
    id INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT,
    phoneNumber BIGINT UNIQUE KEY NOT NULL,
    surname STRING NOT NULL,
    name STRING NOT NULL,
    patronymic STRING,
    address STRING,
    e_mail STRING
);
CREATE TABLE Orders(
     orderNumber INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT,
     FOREIGN KEY(customerId) REFERENCES Customers(Customers.id) INTEGER NOT NULL,
     data STRING NOT NULL,
     deliveryAddress STRING NOT NULL,
     deliveryCost INTEGER DEFAULT 0,
     delivery STRING NOT NULL,
     payment STRING NOT NULL
);
CREATE TABLE ProductsInOrders(
    FOREIGN KEY(product) REFERENCES Orders(Products.vendorCode) INTEGER NOT NULL,
    FOREIGN KEY(custom) REFERENCES Orders(Orders.orderNumber) INTEGER NOT NULL,
    PRIMARY KEY(product, custom),
    countt INTEGER NOT NULL
)
