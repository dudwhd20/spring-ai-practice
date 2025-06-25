CREATE TABLE leave (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    leave_count INT NOT NULL,
    year VARCHAR(4)
);

insert into leave (id, user_name, leave_count, "year")
values (1,'홍길동1',15,'2025')
;


insert into leave (id, user_name, leave_count, "year")
values (2,'홍길동2',10,'2025')
;

insert into leave (id, user_name, leave_count, "year")
values (3,'홍길동3',9,'2025')
;


insert into leave (id, user_name, leave_count, "year")
values (4,'홍길동4',8,'2025')
;
