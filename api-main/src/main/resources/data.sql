-- users
insert into app_users (email, first_name, last_name, password, username)
	select 'pablo.ulloac@usach.cl' email, 'pablo' first_name, 'ulloa' last_name, 'mddv' password, 'admin' username from dual
	where not exists (select username, email from app_users where username = 'admin' or email = 'pablo.ulloac@usach.cl');
insert into app_users (email, first_name, last_name, password, username)
	select 'pulloa@avla.com' email, 'test' first_name, 'user' last_name, 'mddv' password, 'test' username from dual
	where not exists (select username, email from app_users where username = 'test' or email = 'pulloa@avla.com');

-- roles

insert into roles (description, name)
	select 'system global admin' description, 'sadmin' name from dual
	where not exists (select name from roles where name = 'sadmin');

insert into roles (description, name)
	select 'appuser admin' description, 'admin' name from dual
	where not exists (select name from roles where name = 'admin');

insert into roles (description, name)
	select 'regular appuser' description, 'user' name from dual
	where not exists (select name from roles where name = 'user');

-- user_roles

insert into user_roles (user_id, role_id)
	select 1 user_id, 1 role_id from dual
	where not exists (select user_id, role_id from user_roles where user_id = 1 and role_id = 1);
insert into user_roles (user_id, role_id)
	select 1 user_id, 2 role_id from dual
	where not exists (select user_id, role_id from user_roles where user_id = 1 and role_id = 2);
insert into user_roles (user_id, role_id)
	select 2 user_id, 3 role_id from dual
	where not exists (select user_id, role_id from user_roles where user_id = 2 and role_id = 3);

-- organizational_units

insert into organizational_units (name, creation_timestamp, modification_timestamp)
	select 'admins' name, current_timestamp creation_timestamp, current_timestamp modification_timestamp from dual
	where not exists (select name from organizational_units where name = 'admins');
insert into organizational_units (name, creation_timestamp, modification_timestamp)
	select 'developers' name, current_timestamp creation_timestamp, current_timestamp modification_timestamp from dual
	where not exists (select name from organizational_units where name = 'developers');

-- organizational_unit_users

insert into organizational_unit_users
	select 1 user_id, 1 organizational_unit_id from dual
	where not exists (select user_id, organizational_unit_id from organizational_unit_users where user_id = 1 and organizational_unit_id = 1);
insert into organizational_unit_users
	select 1 user_id, 2 organizational_unit_id from dual
	where not exists (select user_id, organizational_unit_id from organizational_unit_users where user_id = 1 and organizational_unit_id = 2);

-- organizational_unit_roles

insert into organizational_unit_roles
	select 2 role_id, 1 organizational_unit_id from dual
	where not exists (select role_id, organizational_unit_id from organizational_unit_roles where role_id = 2 and organizational_unit_id = 1);
insert into organizational_unit_roles
	select 3 role_id, 2 organizational_unit_id from dual
	where not exists (select role_id, organizational_unit_id from organizational_unit_roles where role_id = 3 and organizational_unit_id = 2);

-- extractors
insert into extractors (name, api_url, supported_engine, supported_versions)
	select 'mysql-extractor' name, 'http://188.166.34.128:8081/mddv-mysql-extractor/api/v1/extractor' api_url, 'mysql' supported_engine, '5.5,5.6,5.7' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'mysql-extractor' and supported_engine = 'mysql' and supported_versions = '5.5,5.6,5.7');
insert into extractors (name, api_url, supported_engine, supported_versions)
	select 'arangodb-extractor' name, 'http://188.166.34.128:8082/mddv-arangodb-extractor/api/v1/extractor' api_url, 'arangodb' supported_engine, '3.1' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'arangodb-extractor' and supported_engine = 'arangodb' and supported_versions = '3.1');
insert into extractors (name, api_url, supported_engine, supported_versions)
	select 'postgres-extractor' name, 'http://188.166.34.128:8083/mddv-postgres-extractor/api/v1/extractor' api_url, 'postgres' supported_engine, '10.4,9.6,9.5,9.4,8.2' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'postgres-extractor' and supported_engine = 'postgres' and supported_versions = '10.4,9.6,9.5,9.4,8.2');
insert into extractors (name, api_url, supported_engine, supported_versions)
	select 'mongodb-extractor' name, 'http://188.166.34.128:8084/mddv-mongodb-extractor/api/v1/extractor' api_url, 'mongodb' supported_engine, '3.4' supported_versions
	where not exists (select name, supported_engine, supported_versions from extractors where name = 'mongodb-extractor' and supported_engine = 'mongodb' and supported_versions = '3.4');
