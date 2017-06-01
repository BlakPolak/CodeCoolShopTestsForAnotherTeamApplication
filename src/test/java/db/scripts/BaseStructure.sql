BEGIN TRANSACTION;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS baskets;
CREATE TABLE users
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(100),
  adres TEXT NOT NULL,
  phone TEXT NOT NULL,
  email TEXT
);
CREATE TABLE suppliers
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL
  , description TEXT NULL);
CREATE TABLE products
(id INTEGER PRIMARY KEY AUTOINCREMENT,
 name VARCHAR(100),
 description TEXT,
 price DOUBLE DEFAULT 0.00,
 category_id INTEGER,
 supplier_id INTEGER,
  CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id),
  CONSTRAINT products_suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (id));
CREATE TABLE orders
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER NOT NULL,
  paid BOOLEAN NOT NULL,
  send BOOLEAN NOT NULL
);
CREATE TABLE categories
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL
  , department TEXT NULL, description TEXT NULL);
CREATE TABLE baskets
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  order_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  quantity INTEGER NOT NULL
);
CREATE UNIQUE INDEX categories_id_uindex ON categories (id);
COMMIT;