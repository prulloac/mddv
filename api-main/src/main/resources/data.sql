-- users
INSERT INTO users (email, first_name, last_name, password, username)
	VALUES  ('pablo.ulloac@usach.cl', 'Pablo', 'Ulloa', 'mddv', 'admin');

-- roles

INSERT INTO roles (description, name)
	VALUES  ('System Global Admin', 'sadmin');

INSERT INTO roles (description, name)
	VALUES  ('User Admin', 'admin');

INSERT INTO roles (description, name)
	VALUES  ('Regular User', 'user');

-- user_roles

INSERT INTO user_roles (user_id, role_id) VALUES (1,1);
INSERT INTO user_roles (user_id, role_id) VALUES (1,2);
INSERT INTO user_roles (user_id, role_id) VALUES (1,3);

