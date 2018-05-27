insert into sys_user(username, password, enabled) values("admin","$2a$10$ze4MwfYe8VIbWg75wrmST.ACI7iOhDXs4QDYiMroa3EvnUskM/iFq", 1);
insert into sys_user(username, password, enabled) values("test","$2a$10$.6muQCztrNJ8W6vA30MltuKHHgfJp8651fdXVXiKTPeL7urGpAXgS", 1);

insert into sys_role(name) values("admin");
insert into sys_role(name) values("test");

insert into sys_permission(name) values("ROLE_INDEX");
insert into sys_permission(name) values("ROLE_ROOM_SHOW");
insert into sys_permission(name) values("ROLE_ROOM_CHANGE");


insert sys_role_permission(sys_role_id, permission_id) values(1, 1);
insert sys_role_permission(sys_role_id, permission_id) values(1, 2);
insert sys_role_permission(sys_role_id, permission_id) values(1, 3);
insert sys_role_permission(sys_role_id, permission_id) values(2, 1);


insert into sys_user_role(sys_user_id, role_id) values(1, 1);
insert into sys_user_role(sys_user_id, role_id) values(2, 2);