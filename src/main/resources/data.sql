CREATE TABLE IF NOT EXISTS USERS
(
    id      INT NOT NULL,
    consent BIT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS DIALOGS
(
    id                   INT,
    customerId           INT,
    conversationLanguage VARCHAR(2),
    conversationText     TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (customerId) REFERENCES users (id)
);