CREATE TABLE IF NOT EXISTS aparthotel.users (
user_id INT NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password INT NOT NULL,
PRIMARY KEY (user_id),
UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS aparthotel.roles (
role_id INT NOT NULL,
guest BOOLEAN NOT NULL,
admin BOOLEAN NOT NULL,
PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS aparthotel.users_roles (
user_id INT NOT NULL,
role_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES aparthotel.users (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
FOREIGN KEY (role_id) REFERENCES aparthotel.roles (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS aparthotel.rooms (
room_id INT NOT NULL,
number_of_guests INT NOT NULL,
number_of_beds INT NOT NULL,
kitchen BOOLEAN,
tv BOOLEAN,
washer BOOLEAN,
hair_dryer BOOLEAN,
air_conditioning BOOLEAN,
PRIMARY KEY (room_id)
);

CREATE TABLE IF NOT EXISTS aparthotel.bookings (
booking_id INT NOT NULL,
room_id INT NOT NULL,
user_id INT NOT NULL,
arrival_date TIMESTAMP NOT NULL,
departure_date TIMESTAMP NOT NULL,
total_price DECIMAL NOT NULL,
PRIMARY KEY (booking_id),
FOREIGN KEY (room_id) REFERENCES aparthotel.rooms (room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
FOREIGN KEY (user_id) REFERENCES aparthotel.users (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS aparthotel.prices (
price_id INT NOT NULL,
room_id INT NOT NULL,
period_start TIMESTAMP NOT NULL,
period_end TIMESTAMP NOT NULL,
price DECIMAL NOT NULL,
PRIMARY KEY (price_id),
FOREIGN KEY (room_id) REFERENCES aparthotel.rooms (room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);
