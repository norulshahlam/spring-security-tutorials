insert ignore into user(id, username, name, password) values 
(10001, 'managers','mr manager','$2a$12$hpN4cl1r/9AuJLk74ctzh.f7shXwqPqJCQAqsdYfbaXjuNENAF.Xq');
insert ignore into user(id, username, name, password) values  
(10002, 'useruser','mr user','$2a$12$qQCBLhv4YqjgCnSOu3fB2uJDrMvAG6sUXoxDGqEAgs/nsNHP6MByu');
insert ignore into role values(1,'ROLE_MANAGER');
insert ignore into role values(2,'ROLE_USER');
insert ignore into user_role values(10001,1);
insert ignore into user_role values(10002,2);