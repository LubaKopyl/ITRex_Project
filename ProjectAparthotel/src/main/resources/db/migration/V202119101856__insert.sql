INSERT INTO users(first_name, last_name, email, password)
VALUES ('Luba', 'Kopyl', 'luba@gmail.com', '12345luba');
INSERT INTO users(first_name, last_name, email, password)
VALUES ('Masha', 'Petrova', 'masha@gmail.com', '12345masha');
INSERT INTO users(first_name, last_name, email, password)
VALUES ('Maksim', 'Morozov', 'maksim@gmail.com', '12345maksim');

INSERT INTO roles(role_name)
VALUES ('admin');
INSERT INTO roles(role_name)
VALUES ('guest');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id)
VALUES (3, 2);

INSERT INTO rooms (number_of_guests, number_of_beds, has_kitchen, has_tv, has_washer, has_hair_dryer, has_air_conditioning)
VALUES (2, 1, 1, 1, 1, 1, 0);
INSERT INTO rooms (number_of_guests, number_of_beds, has_kitchen, has_tv, has_washer, has_hair_dryer, has_air_conditioning)
VALUES (2, 2, 1, 1, 1, 1, 1);
INSERT INTO rooms (number_of_guests, number_of_beds, has_kitchen, has_tv, has_washer, has_hair_dryer, has_air_conditioning)
VALUES (4, 2, 0, 1, 1, 1, 0);

INSERT INTO bookings (room_id, user_id, arrival_date, departure_date, total_price)
VALUES (1, 2, '2022-02-05', '2022-02-09', 120);
INSERT INTO bookings (room_id, user_id, arrival_date, departure_date, total_price)
VALUES (2, 3, '2022-02-10', '2022-02-20', 400);

INSERT INTO prices (room_id, period_start, period_end, price)
VALUES (1, '2022-01-01 00:00:01', '2022-03-01 00:00:01', 30);
INSERT INTO prices (room_id, period_start, period_end, price)
VALUES (2, '2022-01-01 00:00:01', '2022-03-01 00:00:01', 40);
INSERT INTO prices (room_id, period_start, period_end, price)
VALUES (3, '2022-01-01 00:00:01', '2022-03-01 00:00:01', 35);
COMMIT;