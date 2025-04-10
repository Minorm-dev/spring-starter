--liquibase formatted sql

--changeset minorm:1
ALTER TABLE users
ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';

--changeset minorm:2
ALTER TABLE users_aud
ADD COLUMN password VARCHAR(128);

