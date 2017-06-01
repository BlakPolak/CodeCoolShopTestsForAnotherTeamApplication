BEGIN TRANSACTION;

INSERT INTO `users` (id,first_name,last_name,adres,phone,email) VALUES (1,'firstsgf','fsa','fsaf','4214','fsaas'),
 (2,'firstas','fsaf','fsaf','4214','fasf');

INSERT INTO `suppliers` (id,name,description) VALUES (1,'name','description');
INSERT INTO `products` (id,name,description,price,category_id,supplier_id) VALUES (1,'name','description',22.0,1,1),
 (2,'name','description',22.0,1,1),
 (3,'name','description',22.0,1,1),
 (4,'name','description',22.0,0,0),
 (5,'name','description',22.0,0,0),
 (6,'name','description',22.0,0,0),
 (7,'name','description',22.0,0,0);

INSERT INTO `orders` (id,user_id,paid,send) VALUES (1,1,0,0),
 (2,2,0,0);

INSERT INTO `categories` (id,name,department,description) VALUES (1,'name','department','description');

INSERT INTO `baskets` (id,order_id,product_id,quantity) VALUES (1,12,1,3),
 (2,12,1,3),
 (3,12,1,3),
 (4,12,1,3),
 (5,12,1,3),
 (6,12,1,3),
 (7,12,1,3);
COMMIT;
