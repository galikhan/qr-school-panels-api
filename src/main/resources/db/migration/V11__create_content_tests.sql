create table content_test (
    id_         bigserial                    not null,
    content_    bigint        references content(id_),
    input_ varchar(255),
    output_ varchar(255),
    created_ timestamp,
    updated_ timestamp default now(),
    is_removed_ boolean default false,
    primary key (id_)
);