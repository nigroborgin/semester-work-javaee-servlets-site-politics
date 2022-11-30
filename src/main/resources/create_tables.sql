create table if not exists book
(
    id serial,
    author varchar(255) default 'Unknown'::character varying not null,
    title varchar(255) default 'Unknown'::character varying not null,
    description varchar(5000),
    file_url varchar,
    constraint book_pk
        primary key (id)
);

create table if not exists role_of_user
(
    id serial,
    name varchar(255) not null,
    constraint role_of_user_pk
        primary key (id)
);

create table if not exists account
(
    id serial,
    role_id integer default 2,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    picture_url varchar(255),
    constraint account_pk
        primary key (id),
    constraint account_role_id_fk
        foreign key (role_id) references role_of_user
);

create unique index if not exists account_username_uindex
    on account (username);

create unique index if not exists account_email_uindex
    on account (email);

create table if not exists post
(
    id serial,
    user_id integer not null,
    title varchar(255) default 'not title'::character varying not null,
    date timestamp,
    author varchar(255) not null,
    text varchar,
    type varchar(25),
    constraint post_pk
        primary key (id),
    constraint post_user_id_fk
        foreign key (user_id) references account
);

create table if not exists comment
(
    id serial,
    user_id integer not null,
    post_id integer not null,
    response_to_comment_id integer,
    text varchar(5000) not null,
    constraint comment_pk
        primary key (id),
    constraint comment_post_id_fk
        foreign key (post_id) references post,
    constraint comment_user_id_fk
        foreign key (user_id) references account
);

create table if not exists post_book
(
    post_id integer not null,
    book_id integer not null,
    constraint post_book_pk
        primary key (post_id, book_id),
    constraint post_book_post_id_fk
        foreign key (post_id) references post
            on update cascade on delete cascade,
    constraint post_book_book_id_fk
        foreign key (book_id) references book
            on update cascade on delete cascade
);

