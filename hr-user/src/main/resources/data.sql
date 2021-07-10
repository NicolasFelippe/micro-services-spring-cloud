INSERT INTO user (name, email, password) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$iwiQkQdlfh6E.w9PVl4V1eMTu4Pc19udiyQqXAqAflr0EBHGhK226');
INSERT INTO user (name, email, password) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$iwiQkQdlfh6E.w9PVl4V1eMTu4Pc19udiyQqXAqAflr0EBHGhK226');

INSERT INTO role (role_name) VALUES ('ROLE_OPERATOR');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);