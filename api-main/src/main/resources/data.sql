-- users
INSERT INTO app_users (email, first_name, last_name, password, username)
	select 'pablo.ulloac@usach.cl' email, 'Pablo' first_name, 'Ulloa' last_name, 'mddv' password, 'admin' username from dual
	where not exists (select username, email from app_users where username = 'admin' or email = 'pablo.ulloac@usach.cl');
INSERT INTO app_users (email, first_name, last_name, password, username)
	select 'pulloa@avla.com' email, 'Test' first_name, 'User' last_name, 'mddv' password, 'test' username from dual
	where not exists (select username, email from app_users where username = 'test' or email = 'pulloa@avla.com');

-- roles

INSERT INTO roles (description, name)
	select 'System Global Admin' description, 'sadmin' name from dual
	where not exists (select name from roles where name = 'sadmin');

INSERT INTO roles (description, name)
	select 'AppUser Admin' description, 'admin' name from dual
	where not exists (select NAME from ROLES where NAME = 'admin');

INSERT INTO roles (description, name)
	select 'Regular AppUser' description, 'user' name from dual
	where not exists (select NAME from ROLES where NAME = 'user');

-- user_roles

INSERT INTO user_roles (user_id, role_id)
	select 1 user_id, 1 role_id from dual
	where not exists (select user_id, role_id from USER_ROLES where user_id = 1 and role_id = 1);
INSERT INTO user_roles (user_id, role_id)
	select 1 user_id, 2 role_id from dual
	where not exists (select user_id, role_id from USER_ROLES where user_id = 1 and role_id = 2);
INSERT INTO user_roles (user_id, role_id)
	select 2 user_id, 3 role_id from dual
	where not exists (select user_id, role_id from USER_ROLES where user_id = 2 and role_id = 3);

-- organizational_units

INSERT INTO organizational_units (name, CREATION_TIMESTAMP, MODIFICATION_TIMESTAMP)
	select 'Admins' name, CURRENT_TIMESTAMP creation_timestamp, CURRENT_TIMESTAMP modification_timestamp from dual
	where not exists (select name from ORGANIZATIONAL_UNITS where NAME = 'Admins');
INSERT INTO organizational_units (name, CREATION_TIMESTAMP, MODIFICATION_TIMESTAMP)
	select 'Developers' name, CURRENT_TIMESTAMP creation_timestamp, CURRENT_TIMESTAMP modification_timestamp from dual
	where not exists (select name from ORGANIZATIONAL_UNITS where NAME = 'Developers');

-- organizational_unit_users

INSERT INTO ORGANIZATIONAL_UNIT_USERS
	select 1 user_id, 1 organizational_unit_id from dual
	where not exists (select user_id, organizational_unit_id from ORGANIZATIONAL_UNIT_USERS where user_id = 1 and ORGANIZATIONAL_UNIT_ID = 1);
INSERT INTO ORGANIZATIONAL_UNIT_USERS
	select 1 user_id, 2 organizational_unit_id from dual
	where not exists (select user_id, organizational_unit_id from ORGANIZATIONAL_UNIT_USERS where user_id = 1 and ORGANIZATIONAL_UNIT_ID = 2);

-- organizational_unit_roles

INSERT INTO ORGANIZATIONAL_UNIT_ROLES
	select 2 role_id, 1 organizational_unit_id from dual
	where not exists (select ROLE_ID, organizational_unit_id from ORGANIZATIONAL_UNIT_ROLES where ROLE_ID = 2 and ORGANIZATIONAL_UNIT_ID = 1);
INSERT INTO ORGANIZATIONAL_UNIT_ROLES
	select 3 role_id, 2 organizational_unit_id from dual
	where not exists (select ROLE_ID, organizational_unit_id from ORGANIZATIONAL_UNIT_ROLES where ROLE_ID = 3 and ORGANIZATIONAL_UNIT_ID = 2);

-- extractors
INSERT INTO EXTRACTORS (name, api_url, supported_engine, supported_versions)
	select 'mysql-extractor' name, 'http://188.166.34.128:8081/mddv-mysql-extractor/api/v1/extractor' api_url, 'MySQL' supported_engine, '5.5,5.6,5.7' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'mysql-extractor' and supported_engine = 'MySQL' and supported_versions = '5.5,5.6,5.7');
INSERT INTO EXTRACTORS (name, api_url, supported_engine, supported_versions)
	select 'arangodb-extractor' name, 'http://188.166.34.128:8082/mddv-arangodb-extractor/api/v1/extractor' api_url, 'ArangoDB' supported_engine, '3.1' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'arangodb-extractor' and supported_engine = 'ArangoDB' and supported_versions = '3.1');
INSERT INTO EXTRACTORS (name, api_url, supported_engine, supported_versions)
	select 'h2-extractor' name, 'http://188.166.34.128:8083/mddv-h2-extractor/api/v1/extractor' api_url, 'H2' supported_engine, '1.4.196' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'h2-extractor' and supported_engine = 'H2' and supported_versions = '1.4.196');
INSERT INTO EXTRACTORS (name, api_url, supported_engine, supported_versions)
	select 'mongodb-extractor' name, 'http://188.166.34.128:8084/mddv-mongodb-extractor/api/v1/extractor' api_url, 'MongoDB' supported_engine, '3.4' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'mongodb-extractor' and supported_engine = 'MongoDB' and supported_versions = '3.4');
