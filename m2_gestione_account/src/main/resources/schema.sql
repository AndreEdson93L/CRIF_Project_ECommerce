-- Drop the sequence and table if they already exist
DROP SEQUENCE IF EXISTS users_seq;
DROP TABLE IF EXISTS users;

-- Create sequence for user ID
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 50;

-- Create users table
CREATE TABLE users (
    balance DOUBLE PRECISION,
    id BIGINT PRIMARY KEY DEFAULT nextVal('users_seq'),
    email VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('USER', 'ADMIN'))
);

