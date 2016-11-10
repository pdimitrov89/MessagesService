CREATE DATABASE messages;
USE messages;

CREATE TABLE MESSAGES (
    id varchar(50) NOT NULL,
    type varchar(20) NOT NULL,
    payload varchar(160) NOT NULL,
    created_at timestamp,
    PRIMARY KEY (id)
);
