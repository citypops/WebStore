INSERT INTO accounts (username,password,email,first_name,last_name,city,postcode,street,home_no,phone_no)
VALUES ('admin','pass','-','-','-','-','-','-','-','-');
INSERT INTO accounts (username,password,email,first_name,last_name,city,postcode,street,home_no,phone_no)
VALUES ('karol','pass','karol@asd.pl','Karol','Woźniak','Poznań','56-345','Długa','5/23','(023)123354345');

INSERT INTO user_roles (username,role) VALUES ('admin','ROLE_ADMIN');
INSERT INTO user_roles (username,role) VALUES ('karol','ROLE_CUSTOMER');

INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('123ABC','magnificent soap',2.45,0.23,'soap.jpg');
INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('456DEF','green tea',23.99,0.23,'greentea.jpg');
INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('789CAT','cat',8.39,0.08,'cat.jpg');
INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('756DFG','fish',54,0.23,'fish.jpg');
INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('HSEERS','dog',2,0.23,'dog.jpg');
INSERT INTO products (product_id,product_name,price,tax_amount,image_name)
VALUES ('SFSD34','quad damage',78,0.23,'quad.jpg');
