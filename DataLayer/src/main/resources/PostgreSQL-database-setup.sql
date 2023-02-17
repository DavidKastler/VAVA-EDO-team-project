DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TYPE "read_status" AS ENUM (
    'NOT_SEEN',
    'SEEN'
    );

CREATE TYPE "report_status" AS ENUM (
    'PENDING',
    'ACCEPTED',
    'REJECTED'
    );

CREATE TYPE "friendship" AS ENUM (
    'PENDING',
    'ACCEPTED',
    'BLOCKED'
    );

CREATE TABLE "users"
(
    "u_id"     SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "username" varchar UNIQUE            NOT NULL,
    "password" varchar                   NOT NULL,
    "role_id"  integer                   NOT NULL
);

CREATE TABLE "roles"
(
    "r_id"               SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "role_name"          varchar UNIQUE            NOT NULL,
    "todo_access_rights" boolean DEFAULT false,
    "team_leader_rights" boolean DEFAULT false,
    "manager_rights"     boolean DEFAULT false,
    "admin_rights"       boolean DEFAULT false
);

CREATE TABLE "todos"
(
    "td_id"            SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "user_id"          integer,
    "todo_name"        varchar                   NOT NULL,
    "todo_description" varchar,
    "from_time"        bigint                    NOT NULL,
    "to_time"          bigint                    NOT NULL,
    "completed"        boolean DEFAULT false,
    "group_name"       varchar DEFAULT null
);

CREATE TABLE "groups"
(
    "gr_id"            SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "group_creator_id" integer                   NOT NULL,
    "group_name"       varchar                   NOT NULL
);

CREATE TABLE "group_members"
(
    "gm_id"     SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "group_id"  integer                   NOT NULL,
    "member_id" integer                   NOT NULL
);

CREATE TABLE "chat"
(
    "ch_id"     SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "group_id"  integer                   NOT NULL,
    "sender_id" integer                   NOT NULL,
    "time_sent" bigint                    NOT NULL,
    "message"   varchar                   NOT NULL
);

CREATE TABLE "relationships"
(
    "rel_id"         SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "first_user_id"  integer                   NOT NULL,
    "second_user_id" integer                   NOT NULL,
    "status"         friendship DEFAULT 'PENDING',
    "since"          bigint                    NOT NULL
);

CREATE TABLE "reports"
(
    "rep_id"      SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "reporter_id" integer                   NOT NULL,
    "violator_id" integer                   NOT NULL,
    "rep_message" varchar,
    "status"      report_status DEFAULT 'PENDING'
);

CREATE TABLE "feedback"
(
    "fb_id"      SERIAL UNIQUE PRIMARY KEY NOT NULL,
    "u_id"       integer                   NOT NULL,
    "read"       read_status DEFAULT 'NOT_SEEN',
    "fb_message" varchar                   NOT NULL
);

ALTER TABLE "users"
    ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("r_id");

ALTER TABLE "todos"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("u_id");

ALTER TABLE "groups"
    ADD FOREIGN KEY ("group_creator_id") REFERENCES "users" ("u_id");

ALTER TABLE "group_members"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "group_members"
    ADD FOREIGN KEY ("member_id") REFERENCES "users" ("u_id");

ALTER TABLE "chat"
    ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("gr_id");

ALTER TABLE "chat"
    ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships"
    ADD FOREIGN KEY ("first_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "relationships"
    ADD FOREIGN KEY ("second_user_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports"
    ADD FOREIGN KEY ("reporter_id") REFERENCES "users" ("u_id");

ALTER TABLE "reports"
    ADD FOREIGN KEY ("violator_id") REFERENCES "users" ("u_id");

ALTER TABLE "feedback"
    ADD FOREIGN KEY ("u_id") REFERENCES "users" ("u_id");
