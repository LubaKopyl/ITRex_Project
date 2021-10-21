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

COMMIT;