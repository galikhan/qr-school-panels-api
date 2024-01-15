--create database qr_school_lessons;

create table topic
(
    id_         bigserial not null,
    parent_     bigint references topic(id_),
    name_       varchar(400),
    type_       varchar(30),
    created_ timestamp,
    updated_ timestamp,
    primary key (id_)
);

--ALTER SEQUENCE topic_iseq RESTART WITH 1;
