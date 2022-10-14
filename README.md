# Akka-Http-Assignment


Different APIs are

1.http GET localhost:8080/api/users/${UserName} //To get all users data based on authentication that user name is present in DB. 

2.http GET localhost:8080/api/user/${UserId} //Get user data based on user id

3.http POST "localhost:8080/api/addUser?id=41&name=Karan&startTime=Oct 14&password=kei@123op" here password is optional param.

4.http PATCH "localhost:8080/api/updateUser?id=59&startTime=Oct 13&name=Sham&password=Kety@12po" here except id all are optional params.

5.http DELETE localhost:8080/api/user/${UserId} //Delete user data based on user id


DDL in Mysql DB for USERS table

CREATE TABLE `USERS` (
  `id` int NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `startTime` varchar(500) DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
