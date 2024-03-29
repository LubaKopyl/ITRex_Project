CREATE TABLE IF NOT EXISTS users (
user_id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
PRIMARY KEY (user_id),
UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS roles (
role_id INT NOT NULL AUTO_INCREMENT,
role_name VARCHAR(50) NOT NULL,
PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS users_roles (
user_id INT NOT NULL,
role_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
FOREIGN KEY (role_id) REFERENCES roles (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS rooms (
room_id INT NOT NULL AUTO_INCREMENT,
number_of_guests INT NOT NULL,
number_of_beds INT NOT NULL,
has_kitchen BOOLEAN,
has_tv BOOLEAN,
has_washer BOOLEAN,
has_hair_dryer BOOLEAN,
has_air_conditioning BOOLEAN,
PRIMARY KEY (room_id)
);

CREATE TABLE IF NOT EXISTS bookings (
booking_id INT NOT NULL AUTO_INCREMENT,
room_id INT NOT NULL,
user_id INT NOT NULL,
arrival_date TIMESTAMP NOT NULL,
departure_date TIMESTAMP NOT NULL,
total_price DECIMAL NOT NULL,
PRIMARY KEY (booking_id),
FOREIGN KEY (room_id) REFERENCES rooms (room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
FOREIGN KEY (user_id) REFERENCES users (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS prices (
price_id INT NOT NULL AUTO_INCREMENT,
room_id INT NOT NULL,
period_start TIMESTAMP NOT NULL,
period_end TIMESTAMP NOT NULL,
price DECIMAL NOT NULL,
PRIMARY KEY (price_id),
FOREIGN KEY (room_id) REFERENCES rooms (room_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
);
