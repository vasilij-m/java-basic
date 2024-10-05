INSERT INTO users(login, password, name)
VALUES
('admin', '@dmin', 'admin'),
('user1', 'pswd1', 'user1'),
('user2', 'pswd2', 'user2');

INSERT INTO roles(name)
VALUES
('admin'),
('user');

INSERT INTO users_to_roles(user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 2);