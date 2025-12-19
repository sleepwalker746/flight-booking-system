CREATE TABLE passenger (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    egn VARCHAR(10) UNIQUE NOT NULL,
    address TEXT,
    phone VARCHAR(20),
    passport_number VARCHAR(20)
);