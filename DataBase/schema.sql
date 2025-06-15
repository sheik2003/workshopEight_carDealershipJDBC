# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS car_dealership;

CREATE DATABASE car_dealership;

USE car_dealership;

## Table 1
CREATE TABLE `dealerships` (
    `dealership_id` INT NOT NULL AUTO_INCREMENT,
    `catergory_name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(12) NOT NULL,
    PRIMARY KEY (`dealership_id`)
);


##Table 2
CREATE TABLE `vehicles` (
  `VIN` INT NOT NULL AUTO_INCREMENT,
  `make` VARCHAR(25) NOT NULL,
  `model` VARCHAR(25) NOT NULL,
  `mileage` INT NOT NULL,
  `year` INT(4) NOT NULL,
  `Price` DECIMAL(12,2) NOT NULL,
  `SOLD` BOOLEAN DEFAULT FALSE,
  `color` VARCHAR(20), 
  PRIMARY KEY (VIN)
);


##Table 3
CREATE TABLE inventory (
    `dealership_id` INT NOT NULL,
    `VIN` VARCHAR(17) NOT NULL
);


-- Table 4: sales_contracts
CREATE TABLE sales_contracts (
    `id` INT NOT NULL AUTO_INCREMENT,
    `VIN` INT NOT NULL,
	`customer_name` VARCHAR(100) NOT NULL,
    `customer_email` VARCHAR(100),
    `date_sold` DATE NOT NULL,
    `sale_price` DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

-- DEALERSHIPS
INSERT INTO dealerships (catergory_name, address, phone) VALUES
('Luxury', '123 Park Ave, Manhattan, NY', '2125551234'),
('Economy', '456 Flatbush Ave, Brooklyn, NY', '7185555678'),
('SUVs', '789 Hillside Ave, Queens, NY', '3475559101'),
('Trucks', '321 Bronx Blvd, Bronx, NY', '6465553344'),
('Electric', '555 Greenpoint Ave, Brooklyn, NY', '7185554455');

-- VEHICLES
INSERT INTO vehicles (make, model, mileage, year, Price, SOLD, color) VALUES
('Toyota', 'Camry', 28000, 2021, 22000.00, FALSE, 'White'),       
('Honda', 'Civic', 15000, 2022, 21000.00, TRUE, 'Black'),         
('Ford', 'Explorer', 42000, 2020, 27500.00, FALSE, 'Blue'),       
('BMW', 'X5', 18000, 2023, 58000.00, TRUE, 'Gray'),               
('Tesla', 'Model 3', 12000, 2023, 48000.00, FALSE, 'Red'),        
('Chevrolet', 'Silverado', 65000, 2019, 31000.00, FALSE, 'Silver'),
('Nissan', 'Altima', 36000, 2021, 19500.00, TRUE, 'White'),       
('Jeep', 'Wrangler', 22000, 2022, 35000.00, FALSE, 'Green'),      
('Audi', 'A6', 25000, 2021, 42000.00, TRUE, 'Black'),             
('Hyundai', 'Elantra', 18000, 2023, 20500.00, FALSE, 'Blue');

-- INVENTORY (map dealership IDs to vehicle VINs as strings)
INSERT INTO inventory (dealership_id, VIN) VALUES
(1, '4'),   -- BMW X5
(1, '9'),   -- Audi A6
(2, '1'),   -- Toyota Camry
(2, '2'),   -- Honda Civic
(2, '10'),  -- Hyundai Elantra
(3, '3'),   -- Ford Explorer
(3, '8'),   -- Jeep Wrangler
(4, '6'),   -- Chevy Silverado
(5, '5'),   -- Tesla Model 3
(2, '7');   -- Nissan Altima

-- SALES CONTRACTS (only for SOLD = TRUE vehicles)
INSERT INTO sales_contracts (VIN, customer_name, customer_email, date_sold, sale_price) VALUES
('2', 'John Doe', 'john.doe@example.com', '2024-11-15', 20500.00),
('4', 'Jane Smith', 'jane.smith@example.com', '2025-01-03', 56000.00),
('7', 'Carlos Ramos', 'carlos.r@example.com', '2025-02-12', 19000.00),
('9', 'Emily Zhang', 'emilyz@example.com', '2025-03-25', 41000.00);