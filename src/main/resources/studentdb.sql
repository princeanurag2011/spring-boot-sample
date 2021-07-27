
CREATE DATABASE STUDENTDB;

drop table studentdb.student;

Create Table STUDENTDB.Student  (
  id INTEGER PRIMARY KEY, 
  name varchar(255),
  address varchar(255)
  );
  
INSERT INTO STUDENTDB.STUDENT VALUES (1000, 'KHAN','HYD');
INSERT INTO STUDENTDB.STUDENT VALUES (1001, 'ANURAG','GNT');


SELECT * FROM studentdb.STUDENT;



