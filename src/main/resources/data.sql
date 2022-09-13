DROP TABLE IF EXISTS DIALOG;
DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE IF NOT EXISTS CUSTOMER
(
    id      INT         NOT NULL AUTO_INCREMENT,
    name    VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DIALOG
(
    technical_id          INT        NOT NULL AUTO_INCREMENT,
    id                   INT        NOT NULL,
    customer_id           INT        NOT NULL,
    conversation_language VARCHAR(2) NOT NULL,
    conversation_text     TEXT       NOT NULL,
    consent              BIT        NOT NULL,
    created              TIMESTAMP  NOT NULL,
    PRIMARY KEY (technical_id),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER (id)
);