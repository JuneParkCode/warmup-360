CREATE DATABASE IF NOT EXISTS 'precourse';
USE 'precourse';

CREATE TABLE IF NOT EXISTS 'users'
(
    'id'    INT AUTO_INCREMENT PRIMARY KEY,
    'name'  VARCHAR(255) NOT NULL,
    'score' INT          NOT NULL
)
