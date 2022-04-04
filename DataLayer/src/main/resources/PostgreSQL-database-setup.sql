CREATE TYPE "report_status" AS ENUM (
  'pending',
  'accepted',
  'rejected'
);

CREATE TYPE "friendship" AS ENUM (
  'pending',
  'accepted',
  'blocked'
);

CREATE TABLE "users" (
  "u_id" SERIAL PRIMARY KEY,
  "username" varchar,
  "password" varchar,
  "role_id" integer
);

CREATE TABLE "roles" (
  "r_id" SERIAL PRIMARY KEY,
  "role_name" varchar,
  "todo_access_rights" boolean,
  "team_leader_rights" boolean,
  "admin_rights" boolean
);

CREATE TABLE "todo_table" (
  "td_id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "task_name" varchar,
  "task_description" varchar,
  "due_time" date
);

CREATE TABLE "assignments" (
  "ass_id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "title" varchar,
  "ass_start" date,
  "ass_end" date
);

CREATE TABLE "groups" (
  "gr_id" SERIAL PRIMARY KEY,
  "group_name" varchar,
  "group_creator_id" integer
);

CREATE TABLE "group_members" (
  "group_id" integer,
  "member_id" integer
);

CREATE TABLE "chat" (
  "ch_id" SERIAL PRIMARY KEY,
  "group_id" integer,
  "sender_id" integer,
  "time_sent" date,
  "message" varchar
);

CREATE TABLE "relationships" (
  "first_user_id" integer,
  "second_user_id" integer,
  "status" friendship,
  "since" date
);

CREATE TABLE "reports" (
  "rep_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "ch_id" integer NOT NULL,
  "reporter_id" integer NOT NULL,
  "violator_id" integer NOT NULL,
  "rep_message" varchar(255),
  "status" report_status
);

CREATE TABLE "feedback" (
  "fb_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
  "u_id" integer NOT NULL,
  "fb_message" varchar(255)
);

ALTER TABLE "users" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("r_id");

ALTER TABLE "todo_table" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("u_id");

ALTER TABLE "assignments" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("u_id");

ALTER TABLE "groups" ADD FOREIGN KEY ("group_creator_id") REFERENCES "users" ("u_id");

ALTER TABLE "group_members" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "group_members" ADD FOREIGN KEY ("member_id") REFERENCES "users" ("u_id");

ALTER TABLE "chat" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "chat" ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships" ADD FOREIGN KEY ("first_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships" ADD FOREIGN KEY ("second_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports" ADD FOREIGN KEY ("ch_id") REFERENCES "chat" ("ch_id");

ALTER TABLE "reports" ADD FOREIGN KEY ("reporter_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports" ADD FOREIGN KEY ("violator_id") REFERENCES "users" ("u_id");

ALTER TABLE "feedback" ADD FOREIGN KEY ("u_id") REFERENCES "users" ("u_id");
