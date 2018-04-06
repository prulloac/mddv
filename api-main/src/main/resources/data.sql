-- users
INSERT INTO users (email, first_name, last_name, password, username)
	VALUES  ('pablo.ulloac@usach.cl', 'Pablo', 'Ulloa', 'mddv', 'admin');
INSERT INTO users (email, first_name, last_name, password, username)
	VALUES  ('pablo.ulloac@usach.cl', 'Test', 'User', 'mddv', 'test');

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
INSERT INTO user_roles (user_id, role_id) VALUES (2,3);

-- organizational_units

INSERT INTO organizational_units (name, CREATION_TIMESTAMP, MODIFICATION_TIMESTAMP) values ('Admins', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO organizational_units (name, CREATION_TIMESTAMP, MODIFICATION_TIMESTAMP) values ('Developers', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

-- organizational_unit_users

insert into ORGANIZATIONAL_UNIT_USERS (USER_ID, ORGANIZATIONAL_UNIT_ID) values (1,1);
insert into ORGANIZATIONAL_UNIT_USERS (USER_ID, ORGANIZATIONAL_UNIT_ID) values (1,2);
insert into ORGANIZATIONAL_UNIT_USERS (USER_ID, ORGANIZATIONAL_UNIT_ID) values (2,2);

-- organizational_unit_roles

insert into ORGANIZATIONAL_UNIT_ROLES (ROLE_ID, ORGANIZATIONAL_UNIT_ID) values (2,1);
insert into ORGANIZATIONAL_UNIT_ROLES (ROLE_ID, ORGANIZATIONAL_UNIT_ID) values (3,2);