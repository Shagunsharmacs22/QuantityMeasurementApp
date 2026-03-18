CREATE TABLE quantity_measurement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    value DOUBLE,
    unit VARCHAR(50),
    type VARCHAR(50),
    operation VARCHAR(50),
    created_at TIMESTAMP
);