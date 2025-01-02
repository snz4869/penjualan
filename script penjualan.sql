-- 1. Create the database
CREATE DATABASE penjualan;

-- 2. Use the database
USE penjualan;

-- 3. Create tables
CREATE TABLE products (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    price NUMERIC(10,2),
    stock INTEGER,
    created_at TIMESTAMP DEFAULT NOW(),
    created_by VARCHAR(50),
    updated_at TIMESTAMP,
    updated_by VARCHAR(50),
    deleted_at TIMESTAMP,
    deleted_by VARCHAR(50),
    code VARCHAR(100),
    description VARCHAR(255),
    is_active BOOLEAN
);

CREATE TABLE transaksi (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    total_harga NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT NOW(),
    created_by VARCHAR(255),
    updated_at TIMESTAMP,
    updated_by VARCHAR(255),
    deleted_at TIMESTAMP,
    deleted_by VARCHAR(255),
    status VARCHAR(50),
    remarks TEXT
);

CREATE TABLE transaction_details (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    transaction_id VARCHAR(255),
    product_code VARCHAR(255),
    quantity INTEGER,
    price NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT NOW(),
    created_by VARCHAR(50),
    updated_at TIMESTAMP,
    updated_by VARCHAR(50),
    deleted_at TIMESTAMP,
    deleted_by VARCHAR(50)
);

CREATE TABLE users (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    created_by VARCHAR(50),
    updated_at TIMESTAMP,
    updated_by VARCHAR(50), 
    deleted_at TIMESTAMP,
    deleted_by VARCHAR(50),
    email VARCHAR(255),
    name VARCHAR(255),
    role VARCHAR(255)
);

-- 4. Insert data
INSERT INTO products (id, name, price, stock, created_at, created_by, code, description, is_active)
VALUES 
('41fb9e09-1560-40b1-a215-1819b655ee5b', 'Nasi', 8000.00, 11, '2025-01-02 11:36:00', 'admin', 'N123', 'Nasi 1 bungkus', TRUE),
('cbf13cb6-ea8a-4f10-8dee-2496af8ffb77', 'Beras', 200000.00, 18, '2025-01-02 11:25:50', 'admin', 'B123', 'Beras Premium 5kg', FALSE);

INSERT INTO transaksi (id, total_harga, created_at, created_by, status, remarks)
VALUES
('bcacd3d5-16f2-43d8-8a0a-6c03bd5d73d2', 216000.00, '2025-01-02 12:29:00', 'kasir', 'COMPLETED', 'transaksi berhasil'),
('d46a28a3-adcb-4634-a9c4-7386c1c79fa3', 1080000.00, '2025-01-02 21:36:20', 'john_doe', 'REFUND', 'transaction refund'),
('7b458f11-b7bf-41fe-b1df-105e79451956', 1080000.00, '2025-01-02 21:38:15', 'john_doe', 'REFUND', 'transaction refund');

INSERT INTO transaction_details (id, transaction_id, product_code, quantity, price, created_at, created_by)
VALUES
('1417093a-eb5e-431d-b259-c75b6ba7a96f', 'bcacd3d5-16f2-43d8-8a0a-6c03bd5d73d2', 'N123', 2, 8000.00, NULL, NULL),
('85312e97-6e9b-4d03-85fc-b6ccf6de8944', 'bcacd3d5-16f2-43d8-8a0a-6c03bd5d73d2', 'B123', 1, 200000.00, NULL, NULL),
('e70782af-6c9f-4ef3-9b3f-66f86d84f307', 'd46a28a3-adcb-4634-a9c4-7386c1c79fa3', 'N123', 10, 8000.00, '2025-01-02 21:36:20', 'john_doe');

INSERT INTO users (id, username, password, is_active, created_at, created_by, email, name, role)
VALUES
('8edde619-2320-46d6-9362-6726456ba4c1', 'john_doe', '$2a$10$MoVhTlfCRFQaZjpLZy1SSOdXFZ.x9spKKEmmGrkyGYEaMsEG2wMne', TRUE, NULL, NULL, 'john.doe@example.com', 'John Doe', 'ADMIN'),
('e2649c63-1dba-408e-a3c7-4ff40d222aed', 'john_low', '$2a$10$3xqTMWJUiPA8kEq/oYKcrOOWHl19P9OjXmsJ50nGWBHLvXnAVwj9.', TRUE, NULL, NULL, 'john.low@example.com', 'John Low', 'KASIR');
