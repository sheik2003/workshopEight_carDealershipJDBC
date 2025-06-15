# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS car_dealership;

CREATE DATABASE car_dealership;

USE car_dealership;

## Table 1 updated
CREATE TABLE `dealerships` (
    `dealership_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,           
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(12) NOT NULL,
    PRIMARY KEY (`dealership_id`)
);


##Table 2 Updated
CREATE TABLE `vehicles` (
  `VIN` INT NOT NULL AUTO_INCREMENT,
  `year` INT(4) NOT NULL,
  `make` VARCHAR(25) NOT NULL,
  `model` VARCHAR(25) NOT NULL,
  `vehicleType` VARCHAR(25),       
  `color` VARCHAR(20),             
  `mileage` INT NOT NULL,          
  `price` DECIMAL(12,2) NOT NULL,
  `sold` BOOLEAN DEFAULT FALSE,    
  PRIMARY KEY (VIN)
);


##Table 3
CREATE TABLE inventory (
    dealership_id  INT NOT NULL,
    VIN            INT NOT NULL,
    PRIMARY KEY (dealership_id, VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
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

	-- Table 5: Lease contracts
CREATE TABLE lease_contracts (
    lease_contract_id   INT             NOT NULL AUTO_INCREMENT,
    vin                 INT             NOT NULL,
    customer_name       VARCHAR(100)    NOT NULL,
    customer_email      VARCHAR(100),
    date_lease          DATE            NOT NULL,
    expected_end_value  DECIMAL(12,2)   NOT NULL,
    lease_fee           DECIMAL(12,2)   NOT NULL,
    total_price         DECIMAL(12,2)   NOT NULL,
    monthly_payment     DECIMAL(12,2)   NOT NULL,
    PRIMARY KEY (lease_contract_id),
    CONSTRAINT fk_lease_vehicle FOREIGN KEY (vin)
        REFERENCES vehicles(vin)
);



-- DEALERSHIPS
INSERT INTO dealerships (name, address, phone) VALUES
('Luxury', '123 Park Ave, Manhattan, NY', '2125551234'),
('Economy', '456 Flatbush Ave, Brooklyn, NY', '7185555678'),
('SUVs', '789 Hillside Ave, Queens, NY', '3475559101'),
('Trucks', '321 Bronx Blvd, Bronx, NY', '6465553344'),
('Electric', '555 Greenpoint Ave, Brooklyn, NY', '7185554455');

-- VEHICLES
INSERT INTO vehicles (make, model, mileage, year, price, sold, color, vehicleType)
VALUES
('Toyota', 'Camry', 28000, 2021, 22000.00, FALSE, 'White', 'Sedan'),       
('Honda', 'Civic', 15000, 2022, 21000.00, TRUE, 'Black', 'Sedan'),         
('Ford', 'Explorer', 42000, 2020, 27500.00, FALSE, 'Blue', 'SUV'),       
('BMW', 'X5', 18000, 2023, 58000.00, TRUE, 'Gray', 'SUV'),               
('Tesla', 'Model 3', 12000, 2023, 48000.00, FALSE, 'Red', 'Sedan'),        
('Chevrolet', 'Silverado', 65000, 2019, 31000.00, FALSE, 'Silver', 'Truck'),
('Nissan', 'Altima', 36000, 2021, 19500.00, TRUE, 'White', 'Sedan'),       
('Jeep', 'Wrangler', 22000, 2022, 35000.00, FALSE, 'Green', 'SUV'),      
('Audi', 'A6', 25000, 2021, 42000.00, TRUE, 'Black', 'Sedan'),             
('Hyundai', 'Elantra', 18000, 2023, 20500.00, FALSE, 'Blue', 'Sedan');


-- INVENTORY (map dealership IDs to vehicle VINs as strings)
INSERT INTO inventory (dealership_id, VIN) VALUES
(1, 4),   -- BMW X5
(1, 9),   -- Audi A6
(2, 1),   -- Toyota Camry
(2, 2),   -- Honda Civic
(2, 10),  -- Hyundai Elantra
(3, 3),   -- Ford Explorer
(3, 8),   -- Jeep Wrangler
(4, 6),   -- Chevy Silverado
(5, 5),   -- Tesla Model 3
(2, 7);   -- Nissan Altima

INSERT INTO sales_contracts (VIN, customer_name, customer_email, date_sold, sale_price) VALUES
(2, 'John Doe', 'john.doe@example.com', '2024-11-15', 20500.00),
(4, 'Jane Smith', 'jane.smith@example.com', '2025-01-03', 56000.00),
(7, 'Carlos Ramos', 'carlos.r@example.com', '2025-02-12', 19000.00),
(9, 'Emily Zhang', 'emilyz@example.com', '2025-03-25', 41000.00);

INSERT INTO lease_contracts (
    vin, customer_name, customer_email, date_lease, expected_end_value, lease_fee, total_price, monthly_payment
) VALUES
(3, 'Ava Green', 'ava.green@example.com', '2025-04-10', 12000.00, 1500.00, 16500.00, 458.33),
(5, 'Liam Smith', 'liam.smith@example.com', '2025-05-01', 16000.00, 2000.00, 18000.00, 500.00),
(8, 'Noah Brown', 'noah.brown@example.com', '2025-05-15', 10000.00, 1200.00, 14000.00, 388.89);

