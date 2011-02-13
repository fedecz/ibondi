drop table locations IF EXISTS;
create table locations(
      id integer GENERATED BY DEFAULT AS IDENTITY (start with 0, increment by 1) not null primary key,
      name varchar_ignorecase(50) not null,
      latitude float not null,
      longitude float not null);

insert into locations (name, latitude, longitude) values ('60',-34.49023889,-58.50026111);
insert into locations (name, latitude, longitude) values ('59',-34.48916944,-58.50085);
