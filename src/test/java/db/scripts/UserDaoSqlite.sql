INSERT INTO users (first_name, last_name, adres, phone, email) VALUES ('Michael', 'Osak', 'olkusz', '12345', 'osakmichal@gmail.com');
INSERT INTO suppliers (name, description) VALUES ('audi', 'nice cars');
INSERT INTO suppliers (name, description) VALUES ('apple', 'nice phones');
INSERT INTO categories (name, description) VALUES ('cars', 'nice cars cat');
INSERT INTO categories (name, description) VALUES ('phones', 'nice phones cat');
INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('iphone', 'nice phone', 123, 1, 1);
INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('samsung', 'nice phone', 103, 1, 1);
INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('audi', 'nice car', 10300, 2, 2);
INSERT INTO baskets (order_id, product_id, quantity) VALUES (1, 1, 1);
INSERT INTO baskets (order_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO orders (user_id, paid, send) VALUES (1, 1, 1);