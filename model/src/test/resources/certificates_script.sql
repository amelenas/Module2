DROP DATABASE IF EXISTS test;
CREATE DATABASE IF NOT EXISTS test;

USE test;
create table IF NOT EXISTS gift_certificate
(
    id               int auto_increment
        primary key,
    name             varchar(100) null,
    description      varchar(100) null,
    price            double       null,
    duration         int          null,
    create_date      varchar(45)  null,
    last_update_date varchar(45)  null
);

create table IF NOT EXISTS tag
(
    id   int auto_increment
        primary key,
    name varchar(100) null
);

create table IF NOT EXISTS gift_certificate_tag
(
    gift_certificate_id int not null,
    tag_id              int not null,
    primary key (gift_certificate_id, tag_id),
    constraint fk_gift_certificate_has_tag_gift_certificate
        foreign key (gift_certificate_id) references gift_certificate (id)
            on update cascade on delete cascade,
    constraint fk_gift_certificate_has_tag_tag1
        foreign key (tag_id) references tag (id)
            on update cascade on delete cascade
);

create index fk_gift_certificate_has_tag_gift_certificate_idx
    on gift_certificate_tag (gift_certificate_id);

create index fk_gift_certificate_has_tag_tag1_idx
    on gift_certificate_tag (tag_id);


INSERT INTO gift_certificate
(id,
 name,
 description,
 price,
 duration,
 create_date,
 last_update_date)
VALUES (1, 'Cosmetics','Cosmetics description', 100.0, 12, '2022-04-03', '2022-04-03'),
       (2, 'Music store','Music store description', 200.0, 12, '2022-04-03', '2022-04-03'),
       (3, 'Fitness','Fitness description', 90.0, 3, '2022-04-03', '2022-04-03'),
       (4, 'Sushi','Sushi store description', 50.0, 3, '2022-04-03', '2022-04-03'),
       (5, 'Pizza','Pizza store description', 30.0, 3, '2022-04-03', '2022-04-03');

INSERT INTO tag (id, name)
values (1, 'Cosmetics'),
       (2, 'Music store'),
       (3, 'Fitness'),
       (4, 'Food');
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 4)