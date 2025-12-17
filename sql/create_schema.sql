CREATE DATABASE IF NOT EXISTS biblio_codigojava
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER 'app_user'@'%' IDENTIFIED BY 'Egdpababpec';
GRANT ALL PRIVILEGES ON biblio_codigojava.* TO 'app_user'@'%';

USE biblio_codigojava;

CREATE TABLE authors (
                         author_id INT AUTO_INCREMENT PRIMARY KEY,
                         name_author VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE publishers (
                            publisher_id INT AUTO_INCREMENT PRIMARY KEY,
                            name_publisher VARCHAR(100) NOT NULL,
                            address VARCHAR(100),
                            city VARCHAR(40),
                            province VARCHAR(30),
                            postal_code VARCHAR(20),
                            country VARCHAR(30),
                            phone VARCHAR(16),
                            notes VARCHAR(255)
);

CREATE TABLE categories (
                            category_id INT AUTO_INCREMENT PRIMARY KEY,
                            name_category VARCHAR(30) NOT NULL,
                            subtopic_category VARCHAR(30)
);

CREATE TABLE books (
                       isbn VARCHAR(13) PRIMARY KEY,
                       title VARCHAR(55) NOT NULL,
                       pages INT,
                       summary VARCHAR(255),
                       edition_date DATE,
                       book_cover VARCHAR(255),
                       book_file VARCHAR(255),
                       language VARCHAR(20),
                       authors VARCHAR(100),
                       author_id INT,
                       publisher_id INT,
                       category_id INT,
                       FOREIGN KEY (author_id) REFERENCES authors(author_id)
                           ON DELETE RESTRICT ON UPDATE CASCADE,
                       FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id)
                           ON DELETE RESTRICT ON UPDATE CASCADE,
                       FOREIGN KEY (category_id) REFERENCES categories(category_id)
                           ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       fullname VARCHAR(100) NOT NULL,
                       dni VARCHAR(20) UNIQUE,
                       address VARCHAR(100),
                       city VARCHAR(40),
                       province VARCHAR(30),
                       postal_code VARCHAR(20),
                       country VARCHAR(30),
                       phone VARCHAR(16),
                       email VARCHAR(120),
                       password VARCHAR(255),
                       registration_date DATE,
                       user_drop BOOLEAN DEFAULT FALSE,
                       days_disciplinary INT DEFAULT 0,
                       rol VARCHAR(20)
);

CREATE TABLE loans (
                       loan_id INT AUTO_INCREMENT PRIMARY KEY,
                       loan_date DATE,
                       return_date DATE,
                       user_id INT,
                       isbn VARCHAR(13),
                       FOREIGN KEY (user_id) REFERENCES users(user_id)
                           ON DELETE RESTRICT ON UPDATE CASCADE,
                       FOREIGN KEY (isbn) REFERENCES books(isbn)
                           ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE histories (
                           history_id INT AUTO_INCREMENT PRIMARY KEY,
                           loan_id INT,
                           date_feedback DATE,
                           feedback VARCHAR(255),
                           FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
                               ON DELETE CASCADE ON UPDATE CASCADE
);