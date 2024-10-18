CREATE TABLE `USERS`
(
    `user_id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(10)  NOT NULL,
    `email`      VARCHAR(100) NOT NULL UNIQUE,
    `password`   VARCHAR(100) NOT NULL,
    `phone`      VARCHAR(15)  NOT NULL UNIQUE,
    `address`    VARCHAR(100) NOT NULL,
    `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `CATEGORY`
(
    `category_id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`category_id`)
);

CREATE TABLE `SUBCATEGORY`
(
    `sub_category_id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `category_id`       BIGINT      NOT NULL,
    `sub_category_name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`sub_category_id`),
    FOREIGN KEY (`category_id`) REFERENCES `CATEGORY` (`category_id`)
);

CREATE TABLE `PRODUCT`
(
    `product_id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `product_code`    VARCHAR(100) NOT NULL,
    `product_name`    VARCHAR(100) NOT NULL,
    `cost`            INT          NOT NULL,
    `sub_category_id` BIGINT       NOT NULL,
    `created_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`product_id`),
    FOREIGN KEY (`sub_category_id`) REFERENCES `SUBCATEGORY` (`sub_category_id`)
);

CREATE TABLE `PRODUCT_OPTION`
(
    `option_id`  BIGINT       NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT       NOT NULL,
    `size`       VARCHAR(100) NOT NULL,
    `stock`      INT          NOT NULL,
    `sale_price`      INT          NOT NULL,
    PRIMARY KEY (`option_id`),
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`product_id`)
);

CREATE TABLE `CART`
(
    `cart_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`cart_id`),
    FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`)
);

CREATE TABLE `CART_DETAIL`
(
    `cart_detail_id` BIGINT NOT NULL AUTO_INCREMENT,
    `cart_id`        BIGINT NOT NULL,
    `option_id`      BIGINT NOT NULL,
    PRIMARY KEY (`cart_detail_id`),
    FOREIGN KEY (`cart_id`) REFERENCES `CART` (`cart_id`),
    FOREIGN KEY (`option_id`) REFERENCES `PRODUCT_OPTION` (`option_id`)
);


CREATE TABLE `ORDERS`
(
    `order_id`     BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`      BIGINT       NOT NULL,
    `order_number` VARCHAR(100) NOT NULL,
    `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`)
);

CREATE TABLE `ORDER_PRODUCT`
(
    `order_product_id` BIGINT NOT NULL AUTO_INCREMENT,
    `order_id`         BIGINT NOT NULL,
    `product_id`       BIGINT NOT NULL,
    `option_id`        BIGINT NOT NULL,
    `order_quantity`   INT    NOT NULL,
    `order_price`      INT    NOT NULL,
    PRIMARY KEY (`order_product_id`),
    FOREIGN KEY (`order_id`) REFERENCES `ORDERS` (`order_id`),
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`product_id`),
    FOREIGN KEY (`option_id`) REFERENCES `PRODUCT_OPTION` (`option_id`)
);
CREATE TABLE `PAYMENT`
(
    `payment_id`  BIGINT      NOT NULL AUTO_INCREMENT,
    `order_id`    BIGINT      NOT NULL,
    `type`        VARCHAR(10) NOT NULL,
    `total_price` INT         NOT NULL,
    `status`      INT         NOT NULL,
    `created_at`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`payment_id`),
    foreign key (`order_id`) references `ORDERS` (`order_id`)
);
