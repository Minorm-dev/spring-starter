--liquibase formatted sql

--changeset minorm:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset minorm:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);
