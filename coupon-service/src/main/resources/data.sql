
insert ignore into user(first_name, last_name,email,password) values ('doug','bailey','doug@bailey.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
insert ignore into user(first_name, last_name,email,password) values ('john','ferguson','john@ferguson.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

insert ignore into role values(1,'ROLE_ADMIN');
insert ignore into role values(2,'ROLE_USER');

insert ignore into user_role values(1,1);
insert ignore into user_role values(2,2);
