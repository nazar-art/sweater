INSERT INTO usr (username, password, active)
VALUES ('admin', '123', true);

INSERT INTO user_role (user_id, roles)
VALUES (1, 'USER'),
       (1, 'ADMIN');

