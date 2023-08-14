CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

create table message
(
    id       SERIAL UNIQUE,
    filename VARCHAR(255),
    tag      VARCHAR(255),
    text     VARCHAR(2048) NOT NULL,
    user_id  INT4,
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id INT4 NOT NULL,
    roles   VARCHAR(255)
);

CREATE TABLE usr
(
    id              SERIAL UNIQUE,
    activation_code VARCHAR(255),
    active          BOOLEAN      NOT NULL,
    email           VARCHAR(255),
    password        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS message
    ADD CONSTRAINT message_user_fk
        FOREIGN KEY (user_id) REFERENCES usr;

ALTER TABLE IF EXISTS user_role
    ADD CONSTRAINT user_role_user_fk
        FOREIGN KEY (user_id) REFERENCES usr;