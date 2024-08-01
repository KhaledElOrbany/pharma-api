alter table "user" add district varchar(255), add city_id bigint;

alter table if exists "user"
    add constraint fk_user_city_id
    foreign key ("city_id")
    references city;
