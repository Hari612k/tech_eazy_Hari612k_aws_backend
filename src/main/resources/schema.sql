CREATE TABLE IF NOT EXISTS "user" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vendor_name VARCHAR(255) NOT NULL,
    subscription_type VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS delivery_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date DATE,
    file_link VARCHAR(255),
    vendor_id BIGINT,
    FOREIGN KEY (vendor_id) REFERENCES vendor(id)
);

CREATE TABLE IF NOT EXISTS parcel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    delivery_address VARCHAR(255),
    contact_number VARCHAR(20),
    parcel_size VARCHAR(100),
    parcel_weight DOUBLE,
    tracking_number VARCHAR(100),
    delivery_order_id BIGINT,
    FOREIGN KEY (delivery_order_id) REFERENCES delivery_order(id)
);
