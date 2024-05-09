CREATE TABLE course
(
course_code VARCHAR(6) NOT NULL,
course_name VARCHAR(255),
PRIMARY KEY (course_code)
);

CREATE TABLE section
(
id VARCHAR(8) NOT NULL,
course_code VARCHAR(6),
room_num SMALLINT,
instructor VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (course_code) REFERENCES course(course_code)
);

CREATE TABLE grade
(
id INT NOT NULL AUTO_INCREMENT,
student_name VARCHAR(255),
section_id VARCHAR(8),
score INT,
PRIMARY KEY (id),
FOREIGN KEY (section_id) REFERENCES section(id)
);


INSERT INTO course (course_code, course_name) VALUES
('CS101', 'Intro to Java'),
('CS103',  'Databases');

INSERT INTO section (id, course_code, room_num, instructor) VALUES
('CS101-A', 'CS101', 1802, 'Balderez'),
('CS101-B', 'CS101',  1650, 'Su'),
('CS103-A', 'CS103',  1200, 'Rojas'),
('CS103-B', 'CS103',  1208, 'Tonno');

INSERT INTO grade (student_name, section_id, score) VALUES
('Maya Charlotte', 'CS101-A', 98),
('James Fields', 'CS101-A', 82),
('Michael Alcocer', 'CS101-B', 65),
('Maya Charlotte', 'CS103-A', 89),
('Tomas Lacroix', 'CS101-A', 99),
('Sara Bisat', 'CS101-A', 87),
('James Fields', 'CS101-B', 46),
('Helena Sepulvida', 'CS103-A', 72);