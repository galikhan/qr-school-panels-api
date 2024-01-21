create table content
(
    id_         bigserial not null,
    topic_      bigint references topic(id_),
    type_       varchar(30),
    body_       text,
    input_      varchar(250),
    created_ timestamp default now(),
    updated_ timestamp,
    primary key (id_)
);
