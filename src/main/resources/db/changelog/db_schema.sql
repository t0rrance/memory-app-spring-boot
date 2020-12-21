--liquibase formatted sql

-- changeset mk:create-users
create table users
(
    id SERIAL PRIMARY KEY
    ,name varchar (200)
    ,user_name varchar (200)
    ,password varchar (200)
    ,role varchar (200)
    ,token varchar (500)
);
-- rollback drop table users

-- changeset mk:create-learning_sessions
create table learning_sessions
(
    id SERIAL PRIMARY KEY
    ,user_id INTEGER REFERENCES users(id)
    ,date date
    ,quantity integer
);
-- rollback drop table learning_sessions

-- changeset mk:create-words
create table words
(
    learning_session_id SERIAL REFERENCES learning_sessions(id)
    ,polish varchar (200)
    ,english varchar (200)
);
-- rollback drop table words



