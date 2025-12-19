CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    reservation_code VARCHAR(20) UNIQUE NOT NULL,
    flight_id INT REFERENCES flight (id),
    passenger_id INT REFERENCES passenger (id),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    paid_amount NUMERIC(10, 2)
);