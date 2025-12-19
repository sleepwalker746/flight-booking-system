CREATE TABLE flight (
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(10) UNIQUE NOT NULL,
    airline VARCHAR(100),
    destination VARCHAR(100),
    departure TIMESTAMPTZ NOT NULL,
    price NUMERIC(10,2) NOT NULL ,
    total_seats INT NOT NULL
);