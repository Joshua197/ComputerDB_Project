DROP TABLE computer;
CREATE TABLE computer ( id int not null primary key, 
                              name varchar(40), 
                              type int, 
                              memory int, 
                              disk_capacity int,
                              display_type int);

INSERT INTO computer VALUES(1,'Desktop on B233',0, 2000, 200, 3);
INSERT INTO computer VALUES(2,'My laptop',2, 8000, 120, 3);
INSERT INTO computer VALUES(3,'vili.pmmf.hu server',1, 4000, 1000, 0);
