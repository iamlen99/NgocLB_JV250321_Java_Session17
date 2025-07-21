create schema cinema;

use cinema;

create table movies
(
    movie_id     int primary key auto_increment,
    title        varchar(50),
    director     varchar(50),
    duration     int,
    release_date date,
    status       bit
);

create table showtimes
(
    showtime_id     int primary key auto_increment,
    movie_id        int,
    show_time       datetime,
    total_seats     int,
    available_seats int,
    status          bit,
    constraint sm_movie_id_fk
        foreign key (movie_id)
            references movies (movie_id)
            on delete cascade
);

create table tickets
(
    ticket_id     int primary key auto_increment,
    showtime_id        int,
    seat_number     int,
    customer_name     varchar(50),
    constraint ts_showtime_id_fk
        foreign key (showtime_id)
            references showtimes (showtime_id)
            on delete cascade
);


# Procedure bang movies

DELIMITER $$
create procedure add_movie (
    title_in varchar(50),
    director_in varchar(50),
    duration_in int,
    release_date_in date,
    status_in bit
)
begin
    insert into movies (title, director, duration, release_date, status)
        values (title_in, director_in, duration_in, release_date_in, status_in);
end $$
DELIMITER ;

DELIMITER $$
create procedure update_movie (
    id_in int,
    title_in varchar(50),
    director_in varchar(50),
    duration_in int,
    release_date_in date,
    status_in bit
)
begin
   update movies
       set title = title_in,
           director = director_in,
           duration = duration_in,
           release_date = release_date_in,
           status = status_in
    where movie_id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_id (
    id_in int,
    out count_movie int
)
begin
    select count(*) into  count_movie from movies where movie_id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure find_movie_by_id (
    id_in int
)
begin
    select * from movies where movie_id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure delete_movie_by_id (
    id_in int
)
begin
    delete from movies where movie_id = id_in;
end $$
DELIMITER ;

