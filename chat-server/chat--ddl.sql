CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL,
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS roles (
    role_id SERIAL,
    name varchar(255) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY(role_id)
);

CREATE TABLE IF NOT EXISTS users_to_roles (
    user_id int,
    role_id int,
    CONSTRAINT users_to_roles_pkey PRIMARY KEY(user_id, role_id),
    CONSTRAINT users_to_roles_user_fkey FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT users_to_roles_role_fkey FOREIGN KEY(role_id) REFERENCES roles(role_id)
);
