CREATE TABLE Products(
    vendorCode INTEGER PRIMARY KEY,
    presence INTEGER NOT NULL,
    technicType STRING,
    brand STRING,
    model STRING,
    price INTEGER,
    weight INTEGER,
    height INTEGER,
    lenght INTEGER,
    width INTEGER
);
INSERT INTO Products VALUES(?,?,?,?,?,?,?,?,?,?);
