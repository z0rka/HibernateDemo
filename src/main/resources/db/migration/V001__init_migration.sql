CREATE SCHEMA IF NOT EXISTS my_university;

CREATE SEQUENCE IF NOT EXISTS my_university.my_university_id_seq;

CREATE TABLE IF NOT EXISTS my_university.student
(
    id    integer NOT NULL DEFAULT nextval('my_university.my_university_id_seq') primary key,
    name  text    NOT NULL,
    email text    NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_user_status ON my_university.student USING hash (name);

CREATE TABLE IF NOT EXISTS my_university.mark
(
    id            integer NOT NULL DEFAULT nextval('my_university.my_university_id_seq'),

    fk_student_id integer
        constraint data_source_fk_connection_id_fkey references my_university.student,

    discipline    text    NOT NULL,

    value         float   NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_user_status ON my_university.mark USING hash (discipline);