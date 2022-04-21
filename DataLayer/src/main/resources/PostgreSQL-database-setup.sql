DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

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
                         "u_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                         "username" varchar UNIQUE NOT NULL,
                         "password" varchar NOT NULL,
                         "role_id" integer NOT NULL
);

CREATE TABLE "roles" (
                         "r_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                         "role_name" varchar UNIQUE NOT NULL,
                         "basic_rights" boolean DEFAULT false,
                         "todo_access_rights" boolean DEFAULT false,
                         "team_leader_rights" boolean DEFAULT false,
                         "admin_rights" boolean DEFAULT false
);

CREATE TABLE "todo_group" (
                              "td_g_id" integer PRIMARY KEY NOT NULL,
                              "u_id" integer NOT NULL,
                              "td_group_name" varchar NOT NULL
);

CREATE TABLE "todos" (
                         "td_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                         "group_id" integer NOT NULL,
                         "task_name" varchar NOT NULL,
                         "task_description" varchar,
                         "from_time" bigint NOT NULL,
                         "to_time" bigint NOT NULL,
                         "completed" boolean DEFAULT false,
                         "tag" varchar DEFAULT null
);

CREATE TABLE "groups" (
                          "gr_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                          "group_creator_id" integer NOT NULL,
                          "group_name" varchar NOT NULL
);

CREATE TABLE "group_members" (
                                 "group_id" integer NOT NULL,
                                 "member_id" integer NOT NULL
);

CREATE TABLE "chat" (
                        "ch_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                        "group_id" integer NOT NULL,
                        "sender_id" integer NOT NULL,
                        "time_sent" bigint NOT NULL,
                        "message" varchar NOT NULL
);

CREATE TABLE "relationships" (
                                 "first_user_id" integer NOT NULL,
                                 "second_user_id" integer NOT NULL,
                                 "status" friendship DEFAULT 'pending',
                                 "since" bigint NOT NULL
);

CREATE TABLE "reports" (
                           "rep_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                           "reporter_id" integer NOT NULL,
                           "violator_id" integer NOT NULL,
                           "rep_message" varchar,
                           "status" report_status DEFAULT 'pending'
);

CREATE TABLE "feedback" (
                            "fb_id" SERIAL UNIQUE PRIMARY KEY NOT NULL,
                            "u_id" integer NOT NULL,
                            "fb_message" varchar NOT NULL
);

COMMENT ON TABLE "feedback" IS 'Tu pouzivatel vie povedat co by chcel zmenit';

ALTER TABLE "users" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("r_id");

ALTER TABLE "todos" ADD FOREIGN KEY ("group_id") REFERENCES "todo_group" ("td_g_id");

ALTER TABLE "todo_group" ADD FOREIGN KEY ("u_id") REFERENCES "users" ("u_id");

ALTER TABLE "groups" ADD FOREIGN KEY ("group_creator_id") REFERENCES "users" ("u_id");

ALTER TABLE "group_members" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "group_members" ADD FOREIGN KEY ("member_id") REFERENCES "users" ("u_id");

ALTER TABLE "chat" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "chat" ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships" ADD FOREIGN KEY ("first_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships" ADD FOREIGN KEY ("second_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports" ADD FOREIGN KEY ("reporter_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports" ADD FOREIGN KEY ("violator_id") REFERENCES "users" ("u_id");

ALTER TABLE "feedback" ADD FOREIGN KEY ("u_id") REFERENCES "users" ("u_id");
