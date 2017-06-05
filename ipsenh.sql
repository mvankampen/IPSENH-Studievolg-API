DROP TABLE IF EXISTS User_account CASCADE;
DROP TABLE IF EXISTS User_role CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS restriction CASCADE;
DROP TABLE IF EXISTS Course_owner CASCADE;
DROP TABLE IF EXISTS Course_enrollment CASCADE;
DROP TABLE IF EXISTS Course_restriction CASCADE;
DROP TABLE IF EXISTS ab_restriction CASCADE;
DROP TABLE IF EXISTS course_passed CASCADE;
DROP TABLE IF EXISTS Exam_result CASCADE;
DROP TABLE IF EXISTS Exam CASCADE;

CREATE TABLE User_role (
  role_name VARCHAR(255),
  CONSTRAINT pk_role_name PRIMARY KEY (role_name)
);

CREATE TABLE User_account (
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  insertion VARCHAR(255),
  last_name VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) REFERENCES User_role (role_name),
  CONSTRAINT pk_email PRIMARY KEY (email)
);

CREATE TABLE Course (
  code VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  CONSTRAINT pk_code PRIMARY KEY (code)
);

CREATE TABLE restriction (
  name CHARACTER VARYING(40),
  CONSTRAINT pk_name PRIMARY KEY (name)
);

CREATE TABLE course_restriction (
  restriction CHARACTER VARYING(40) REFERENCES Restriction(name),
  course      CHARACTER VARYING(255) REFERENCES Course(code),
  CONSTRAINT course_restriction_restriction_course_key UNIQUE (restriction, course)
);

CREATE TABLE ab_restriction (
  course          CHARACTER VARYING(255) REFERENCES Course(code),
  required_course CHARACTER VARYING(255) REFERENCES Course(code),
  CONSTRAINT ab_restriction_course_required_course_key UNIQUE (course, required_course)
);

CREATE TABLE Course_enrollment (
  user_email VARCHAR(255) REFERENCES User_account (email),
  course_code VARCHAR(255) REFERENCES Course (code),
  enrollment_date DATE DEFAULT now(),
  CONSTRAINT course_enrollment_user_email_course_code_key UNIQUE (user_email, course_code)
);

CREATE TABLE course_passed (
  course     CHARACTER VARYING(255) REFERENCES Course(code),
  user_email CHARACTER VARYING(255) REFERENCES User_account(email),
  CONSTRAINT course_passed_course_user_email_key UNIQUE (course, user_email)
);

CREATE TABLE Course_owner (
  user_email VARCHAR(255) REFERENCES User_account (email),
  course_code VARCHAR(255) REFERENCES Course (code),
  CONSTRAINT course_owner_user_email_course_code_key UNIQUE (user_email, course_code)
);

CREATE TABLE Exam (
  exam_name VARCHAR(255),
  exam_weight INTEGER NOT NULL,
  course_code VARCHAR(255) REFERENCES Course (code),
  CONSTRAINT pk_exam_name PRIMARY KEY (exam_name),
  CONSTRAINT exam_course_unique UNIQUE (exam_name, course_code)
);

CREATE TABLE Exam_result (
  exam_name VARCHAR(255) REFERENCES Exam (exam_name),
  exam_course VARCHAR(255) REFERENCES Course (code),
  exam_date DATE DEFAULT now(),
  exam_mutation_date DATE DEFAULT now(),
  exam_result decimal NOT NULL,
  user_email VARCHAR(255) REFERENCES User_account (email)
);

INSERT INTO User_role VALUES ('admin');
INSERT INTO User_role VALUES ('moduleleider');
INSERT INTO User_role VALUES ('cursist');

INSERT INTO User_account (email,first_name, last_name, date_of_birth, password, role) VALUES ('user@ipsenh.nl','cursist', 'cursist','1994-09-23', 'password', 'cursist');
INSERT INTO User_account (email,first_name, last_name, date_of_birth, password, role) VALUES ('moduleleider@ipsenh.nl','moduleleider', 'modueleider','1994-09-23', 'password', 'moduleleider');
INSERT INTO User_account (email, first_name, last_name, date_of_birth, password, role) VALUES ('admin@ipsenh.nl', 'admin', 'admin', '1994-09-23', 'password', 'admin');

INSERT INTO Course (code, description, start_date, end_date) VALUES ('IPSENH', 'Project Hoofdfase', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IITORG', 'Inleiding Organisatiekunde', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('ILG1', 'Logica', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('ISCRIPT', 'Scripting', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IKPMD', 'Programming for Mobile Devices', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IRDBMS', 'Relationele databasemanagementsystemen', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IRDM', 'Relationele Databases Modelleren', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IPSEN5', 'Project Software Engineering 5', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IPSEN4', 'Project Software Engineering 4', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IAD1', 'Algoritmen en Datastructuren 1', '2017-05-02', '2017-05-22');
INSERT INTO Course (code, description, start_date, end_date) VALUES ('IIAD', 'Inleiding Algoritmen en Datastructuren', '2017-05-02', '2017-05-22');

INSERT INTO Restriction (name) VALUES ('AB_RESTRICTION');
INSERT INTO Restriction (name) VALUES ('DATE_RESTRICTION');

INSERT INTO Course_restriction (course, restriction) VALUES ('IRDM', 'AB_RESTRICTION');
INSERT INTO Course_restriction (course, restriction) VALUES ('IPSENH', 'DATE_RESTRICTION');

INSERT INTO Course_enrollment (user_email, course_code, enrollment_date) VALUES ('user@ipsenh.nl', 'IPSENH', '2017-05-03');
INSERT INTO Course_enrollment (user_email, course_code, enrollment_date) VALUES ('user@ipsenh.nl', 'IIAD', '2017-05-04');
INSERT INTO Course_enrollment (user_email, course_code, enrollment_date) VALUES ('user@ipsenh.nl', 'IITORG', '2017-05-05');

INSERT INTO Course_owner (user_email, course_code) VALUES ('moduleleider@ipsenh.nl', 'IPSENH');
INSERT INTO Course_owner (user_email, course_code) VALUES ('moduleleider@ipsenh.nl', 'IPSEN5');
INSERT INTO Course_owner (user_email, course_code) VALUES ('moduleleider@ipsenh.nl', 'IAD1');
INSERT INTO Course_owner (user_email, course_code) VALUES ('moduleleider@ipsenh.nl', 'IIAD');
INSERT INTO Course_owner (user_email, course_code) VALUES ('moduleleider@ipsenh.nl', 'IITORG');

INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES ('Toets 15-16 Versie A', 1, 'IPSEN5');
INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES ('Toets 15-16 Versie B', 1, 'IPSEN5');
INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES ('Toets 15-16 Herkansing', 1, 'IPSEN5');
INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES ('Toets 16-17 Versie A', 1, 'IPSENH');
INSERT INTO Exam (exam_name, exam_weight, course_code) VALUES ('Toets 16-17 Versie B', 1, 'IPSENH');

INSERT INTO Exam_result (exam_name, exam_course, exam_result, user_email) VALUES ('Toets 15-16 Herkansing', 'IPSEN5', '7.5', 'user@ipsenh.nl');
INSERT INTO Exam_result (exam_name, exam_course, exam_result, user_email) VALUES ('Toets 16-17 Versie A', 'IPSENH','9.5', 'user@ipsenh.nl');