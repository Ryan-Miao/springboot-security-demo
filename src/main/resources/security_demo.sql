/*
Navicat PGSQL Data Transfer

Source Server         : local-p
Source Server Version : 100300
Source Host           : localhost:5432
Source Database       : security_demo
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100300
File Encoding         : 65001

Date: 2018-05-01 17:38:24
*/


-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS "public"."room";
CREATE TABLE "public"."room" (
"id" int4 NOT NULL,
"comment" varchar(255) COLLATE "default",
"create_date" timestamp(6),
"name" varchar(200) COLLATE "default" NOT NULL,
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('1', 'ROLE_ADMIN');
INSERT INTO "public"."sys_role" VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
"id" int8 NOT NULL,
"enabled" bool NOT NULL,
"last_password_reset_date" timestamp(6),
"password" varchar(255) COLLATE "default",
"username" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('1', 't', '2018-05-01 17:34:17', '$2a$10$ze4MwfYe8VIbWg75wrmST.ACI7iOhDXs4QDYiMroa3EvnUskM/iFq', 'admin');
INSERT INTO "public"."sys_user" VALUES ('2', 't', '2018-05-01 17:34:41', '$2a$10$NrH5OJgsv5CXgxH36q6ZtuwUCkEcIFGYBYXWS7bxapF4PpzaNnlJ.', 'test');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_roles";
CREATE TABLE "public"."sys_user_roles" (
"sys_user_id" int8 NOT NULL,
"roles_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO "public"."sys_user_roles" VALUES ('1', '1');
INSERT INTO "public"."sys_user_roles" VALUES ('2', '2');

-- ----------------------------
-- Alter Sequences Owned By
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table room
-- ----------------------------
ALTER TABLE "public"."room" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."sys_user_roles"
-- ----------------------------
ALTER TABLE "public"."sys_user_roles" ADD FOREIGN KEY ("roles_id") REFERENCES "public"."sys_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_user_roles" ADD FOREIGN KEY ("sys_user_id") REFERENCES "public"."sys_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
