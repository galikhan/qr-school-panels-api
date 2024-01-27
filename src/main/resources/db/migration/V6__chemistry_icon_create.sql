create table chemistry_icon
(
    id_         bigserial not null,
    element_    varchar(10),
    name_       varchar(100),
    path_       varchar(200),
    created_ timestamp,
    updated_ timestamp default now(),
    primary key (id_)
);