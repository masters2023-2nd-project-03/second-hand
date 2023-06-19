DROP TABLE IF EXISTS category, location, member, product, watchlist, product_img, chatroom, chat, member_location CASCADE;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `category`
(
    `category_id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `title`            VARCHAR(120) NOT NULL,
    `category_img_url` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`category_id`)
    );


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `location`
(
    `location_id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `location_details`    VARCHAR(120) NOT NULL,
    `location_shortening` VARCHAR(30)  NOT NULL,
    PRIMARY KEY (`location_id`)
    );


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `member`
(
    `member_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `nickname`    VARCHAR(60)  NOT NULL,
    `profile_url` VARCHAR(200) NOT NULL,
    `oauth_id`    VARCHAR(100) NOT NULL,
    PRIMARY KEY (`member_id`),
    UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
    UNIQUE INDEX `oauth_id_UNIQUE` (`oauth_id` ASC) VISIBLE
    );


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product`
(
    `product_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(120) NOT NULL,
    `price`        INT          NULL,
    `content`      TEXT         NOT NULL,
    `lookup_count` INT          NOT NULL DEFAULT 0,
    `sales_status` VARCHAR(15)  NOT NULL DEFAULT '판매중',
    `create_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `category_id`  BIGINT       NOT NULL,
    `location_id`  BIGINT       NOT NULL,
    `member_id`    BIGINT       NOT NULL,
    PRIMARY KEY (`product_id`),
    INDEX `fk_product_category_idx` (`category_id` ASC) VISIBLE,
    INDEX `fk_product_location_idx` (`location_id` ASC) VISIBLE,
    INDEX `fk_product_member_idx` (`member_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`category_id`),
    CONSTRAINT `fk_product_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`location_id`),
    CONSTRAINT `fk_product_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`)
    );


-- -----------------------------------------------------
-- Table `product_img`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product_img`
(
    `product_img_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `img_url`        VARCHAR(300) NOT NULL,
    `product_id`     BIGINT       NOT NULL,
    PRIMARY KEY (`product_img_id`),
    INDEX `fk_product_img_product_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_img_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`)
    );


-- -----------------------------------------------------
-- Table `watchlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `watchlist`
(
    `watchlist_id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id`   BIGINT NOT NULL,
    `member_id`    BIGINT NOT NULL,
    PRIMARY KEY (`watchlist_id`),
    INDEX `fk_watchlist_member_idx` (`member_id` ASC) VISIBLE,
    INDEX `fk_watchlist_product_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_watchlist_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`),
    CONSTRAINT `fk_watchlist_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`)
    );


-- -----------------------------------------------------
-- Table `chatroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chatroom`
(
    `chatroom_id` BIGINT   NOT NULL AUTO_INCREMENT,
    `create_at`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `product_id`  BIGINT   NOT NULL,
    `seller_id`   BIGINT   NOT NULL,
    `buyer_id`    BIGINT   NOT NULL,
    PRIMARY KEY (`chatroom_id`),
    INDEX `fk_chatroom_product_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_chatroom_seller_idx` (`seller_id` ASC) VISIBLE,
    INDEX `fk_chatroom_buyer_idx` (`buyer_id` ASC) VISIBLE,
    CONSTRAINT `fk_chatroom_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`),
    CONSTRAINT `fk_chatroom_seller`
    FOREIGN KEY (`seller_id`)
    REFERENCES `member` (`member_id`),
    CONSTRAINT `fk_chatroom_buyer`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `member` (`member_id`)
    );


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`
(
    `chat_id`     BIGINT      NOT NULL AUTO_INCREMENT,
    `sender_id`   BIGINT      NOT NULL,
    `content`     TEXT        NOT NULL,
    `read_status` VARCHAR(10) NOT NULL DEFAULT 'unread',
    `chatroom_id` BIGINT      NOT NULL,
    PRIMARY KEY (`chat_id`),
    INDEX `fk_chat_chatroom_idx` (`chatroom_id` ASC) VISIBLE,
    INDEX `fk_chat_member_idx` (`sender_id` ASC) VISIBLE,
    CONSTRAINT `fk_chat_chatroom`
    FOREIGN KEY (`chatroom_id`)
    REFERENCES `chatroom` (`chatroom_id`),
    CONSTRAINT `fk_chat_member`
    FOREIGN KEY (`sender_id`)
    REFERENCES `member` (`member_id`)
    );


-- -----------------------------------------------------
-- Table `member_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `member_location`
(
    `member_location_id`   BIGINT  NOT NULL AUTO_INCREMENT,
    `member_id`     BIGINT  NOT NULL,
    `location_id` BIGINT  NOT NULL,
    `main_location_status` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`member_location_id`),
    INDEX `fk_member_location_location_idx` (`location_id` ASC) VISIBLE,
    INDEX `fk_member_location_member_idx` (`member_id` ASC) VISIBLE,
    CONSTRAINT `fk_member_location_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`),
    CONSTRAINT `fk_member_location_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`location_id`)
    );
