# PSMS
MCA Major Project

# Database User & Database Server Configuration
Create a DB user below information 
username "root"
Password as "Boomer#5678"
MySQL DB server should be running on "localhost" port "3306"

# Database Scheme
CREATE SCHEMA `psms`;

CREATE TABLE `psms`.`login` (
  `input_username` varchar(255) NOT NULL,
  `input_password` varchar(255) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`input_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `psms`.`login` (`input_username`, `input_password`, `admin_name`) VALUES ("testuser@gmail.com", "test123", "Testing User");

CREATE TABLE `psms`.`math` (
  `name` varchar(100) NOT NULL,
  `phone` bigint NOT NULL,
  `address` text,
  `fees` decimal(8,2) DEFAULT '0.00',
  `paid` decimal(8,2) DEFAULT '0.00',
  `balance` decimal(8,2) DEFAULT '0.00',
  `roll` varchar(50) NOT NULL,
  `pic` longblob,
  `si` bigint NOT NULL DEFAULT '1',
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`roll`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `psms`.`mca` (
  `name` varchar(100) NOT NULL,
  `phone` bigint NOT NULL,
  `address` text,
  `fees` decimal(8,2) DEFAULT '0.00',
  `paid` decimal(8,2) DEFAULT '0.00',
  `balance` decimal(8,2) DEFAULT '0.00',
  `roll` varchar(50) NOT NULL,
  `pic` longblob,
  `si` bigint NOT NULL DEFAULT '1',
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`roll`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `psms`.`academics` (
  `roll` varchar(50) NOT NULL,
  `test_name` varchar(100) NOT NULL,
  `total_mark` int NOT NULL,
  `obt_mark` int NOT NULL,
  `percent` decimal(5,2) NOT NULL,
  PRIMARY KEY (`roll`,`test_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

