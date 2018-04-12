CREATE DATABASE IF NOT EXISTS test;
USE test;

CREATE TABLE accounts (
  username   VARCHAR(64)  NOT NULL,
  password   VARCHAR(64)  NOT NULL,
  enabled    TINYINT(1)   NOT NULL DEFAULT 1,
  email      VARCHAR(128) NOT NULL UNIQUE,
  first_name VARCHAR(128) NOT NULL,
  last_name  VARCHAR(128) NOT NULL,
  city       VARCHAR(128) NOT NULL,
  postcode   VARCHAR(32)  NOT NULL,
  street     VARCHAR(128) NOT NULL,
  home_no    VARCHAR(32)  NOT NULL,
  phone_no   VARCHAR(32)  NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE user_roles (
  username VARCHAR(64)   NOT NULL,
  role     VARCHAR(64)   NOT NULL,
  FOREIGN KEY (username) REFERENCES accounts (username) ON UPDATE CASCADE,
  PRIMARY KEY (username,role)
);

CREATE TABLE products (
  product_id   VARCHAR(128)  NOT NULL,
  product_name VARCHAR(128)  NOT NULL UNIQUE,
  price        DECIMAL(13,4) NOT NULL,
  tax_amount   DECIMAL(8,2)  NOT NULL,
  active       TINYINT(1)    NOT NULL DEFAULT 1,
  image_name   VARCHAR(255),
  PRIMARY KEY (product_id)
);

CREATE TABLE orders (
  order_id VARCHAR(128)  NOT NULL,
  username VARCHAR(64)   NOT NULL,
  created  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  state    TINYINT(1)    NOT NULL DEFAULT 0,
  FOREIGN KEY (username) REFERENCES accounts (username) ON UPDATE CASCADE,
  PRIMARY KEY (order_id)
);

CREATE TABLE order_items (
  product_id VARCHAR(128)  NOT NULL,
  quantity   INTEGER       NOT NULL,
  sold_price DECIMAL(13,4) NOT NULL,  # price given at the time of making order
  sold_tax   DECIMAL(8,2)  NOT NULL,  # same as above with tax amount
  order_id   VARCHAR(128)  NOT NULL,
  FOREIGN KEY (order_id)   REFERENCES orders (order_id) ON UPDATE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products (product_id) ON UPDATE CASCADE,
  PRIMARY KEY (product_id,order_id)
);