USE unit_10_db;
CREATE TABLE Location(
                         id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE Route(
                      id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      from_id INT NOT NULL,
                      to_id INT NOT NULL,
                      cost INT NOT NULL,
                      FOREIGN KEY (from_id) REFERENCES Location(id) ON DELETE RESTRICT ON UPDATE CASCADE,
                      FOREIGN KEY (to_id) REFERENCES Location(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Problem(
                        id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        from_id INT NOT NULL,
                        to_id INT NOT NULL,
                        FOREIGN KEY (from_id) REFERENCES Location(id) ON DELETE RESTRICT ON UPDATE CASCADE,
                        FOREIGN KEY (to_id) REFERENCES Location(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Solution(
                         problem_id INT NOT NULL PRIMARY KEY,
                         cost INT,
                         FOREIGN KEY (problem_id) REFERENCES Problem(id) ON DELETE CASCADE ON UPDATE CASCADE,
                         CHECK (cost > 0 AND cost < 200000)
);

ALTER TABLE Route ADD CHECK (cost > 0);

INSERT INTO Location(name) VALUE ('gdansk');
INSERT INTO Location(name) VALUE ('bydgoszcz');
INSERT INTO Location(name) VALUE ('torun');
INSERT INTO Location(name) VALUE ('warszawa');

INSERT INTO Route(from_id, to_id, cost) VALUES (1, 2, 1);
INSERT INTO Route(from_id, to_id, cost) VALUES (1, 3, 3);
INSERT INTO Route(from_id, to_id, cost) VALUES (2, 1, 1);
INSERT INTO Route(from_id, to_id, cost) VALUES (2, 3, 1);
INSERT INTO Route(from_id, to_id, cost) VALUES (2, 4, 4);
INSERT INTO Route(from_id, to_id, cost) VALUES (3, 1, 3);
INSERT INTO Route(from_id, to_id, cost) VALUES (3, 2, 1);
INSERT INTO Route(from_id, to_id, cost) VALUES (3, 4, 1);
INSERT INTO Route(from_id, to_id, cost) VALUES (4, 2, 4);
INSERT INTO Route(from_id, to_id, cost) VALUES (4, 3, 1);

INSERT INTO Problem(from_id, to_id) VALUES (1, 4);
INSERT INTO Problem(from_id, to_id) VALUES (2, 4);