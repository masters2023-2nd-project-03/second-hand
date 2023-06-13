-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category`;

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
DROP TABLE IF EXISTS `location`;

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
DROP TABLE IF EXISTS `member`;

CREATE TABLE IF NOT EXISTS `member`
(
    `member_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `nickname`    VARCHAR(60)  NOT NULL,
    `profile_url` VARCHAR(200) NOT NULL,
    `oauth_id`    VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`member_id`),
    UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
    UNIQUE INDEX `oauth_id_UNIQUE` (`oauth_id` ASC) VISIBLE
    );


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product`;

CREATE TABLE IF NOT EXISTS `product`
(
    `product_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(120) NOT NULL,
    `price`        INT          NULL,
    `content`      TEXT         NOT NULL,
    `lookup_count` INT          NOT NULL DEFAULT 0,
    `sales_status` VARCHAR(15)  NOT NULL DEFAULT 'sale',
    `create_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `category_id`  BIGINT       NOT NULL,
    `location_id`  BIGINT       NOT NULL,
    `member_id`    BIGINT       NOT NULL,
    PRIMARY KEY (`product_id`),
    INDEX `fk_product_category1_idx` (`category_id` ASC) VISIBLE,
    INDEX `fk_product_location1_idx` (`location_id` ASC) VISIBLE,
    INDEX `fk_product_member1_idx` (`member_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_product_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_product_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


-- -----------------------------------------------------
-- Table `product_img`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_img`;

CREATE TABLE IF NOT EXISTS `product_img`
(
    `product_img_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `img_url`        VARCHAR(200) NOT NULL,
    `product_id`     BIGINT       NOT NULL,
    PRIMARY KEY (`product_img_id`),
    INDEX `fk_product_img_product_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_img_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


-- -----------------------------------------------------
-- Table `watchlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`;

CREATE TABLE IF NOT EXISTS `watchlist`
(
    `watchlist_id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id`   BIGINT NOT NULL,
    `member_id`    BIGINT NOT NULL,
    PRIMARY KEY (`watchlist_id`),
    INDEX `fk_product_has_member_member1_idx` (`member_id` ASC) VISIBLE,
    INDEX `fk_product_has_member_product1_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_has_member_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_product_has_member_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chatroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chatroom`;

CREATE TABLE IF NOT EXISTS `chatroom`
(
    `chatroom_id` BIGINT   NOT NULL AUTO_INCREMENT,
    `create_at`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `product_id`  BIGINT   NOT NULL,
    `seller_id`   BIGINT   NOT NULL,
    `buyer_id`    BIGINT   NOT NULL,
    PRIMARY KEY (`chatroom_id`),
    INDEX `fk_chatroom_product1_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_chatroom_member1_idx` (`seller_id` ASC) VISIBLE,
    INDEX `fk_chatroom_member2_idx` (`buyer_id` ASC) VISIBLE,
    CONSTRAINT `fk_chatroom_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_chatroom_member1`
    FOREIGN KEY (`seller_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_chatroom_member2`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`;

CREATE TABLE IF NOT EXISTS `chat`
(
    `chat_id`     BIGINT      NOT NULL AUTO_INCREMENT,
    `sender_id`   BIGINT      NOT NULL,
    `content`     TEXT        NOT NULL,
    `read_status` VARCHAR(10) NOT NULL DEFAULT 'unread',
    `chatroom_id` BIGINT      NOT NULL,
    PRIMARY KEY (`chat_id`),
    INDEX `fk_chat_chatroom1_idx` (`chatroom_id` ASC) VISIBLE,
    INDEX `fk_chat_member1_idx` (`sender_id` ASC) VISIBLE,
    CONSTRAINT `fk_chat_chatroom1`
    FOREIGN KEY (`chatroom_id`)
    REFERENCES `chatroom` (`chatroom_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_chat_member1`
    FOREIGN KEY (`sender_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );


-- -----------------------------------------------------
-- Table `member_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member_location`;

CREATE TABLE IF NOT EXISTS `member_location`
(
    `member_location_id`   BIGINT  NOT NULL AUTO_INCREMENT,
    `member_member_id`     BIGINT  NOT NULL,
    `location_location_id` BIGINT  NOT NULL,
    `main_location_status` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`member_location_id`),
    INDEX `fk_member_has_location_location1_idx` (`location_location_id` ASC) VISIBLE,
    INDEX `fk_member_has_location_member1_idx` (`member_member_id` ASC) VISIBLE,
    CONSTRAINT `fk_member_has_location_member1`
    FOREIGN KEY (`member_member_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_member_has_location_location1`
    FOREIGN KEY (`location_location_id`)
    REFERENCES `location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );
