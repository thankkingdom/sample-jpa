CREATE SCHEMA jpa;
ALTER SCHEMA jpa OWNER TO admin;

CREATE TABLE jpa.department (
    code integer NOT NULL,
    name character varying(20) NOT NULL
);
ALTER TABLE jpa.department OWNER TO admin;

COPY jpa.department (code, name) FROM stdin;
1	総務部
2	技術部
\.

CREATE TABLE jpa.employee (
    no integer NOT NULL,
    birthday date,
    first_name character varying(20) NOT NULL,
    last_name character varying(20) NOT NULL,
    mail_address character varying(255),
    sex character varying(10),
    department_code integer,
    version integer NOT NULL DEFAULT 0
);
ALTER TABLE jpa.employee OWNER TO admin;

COPY jpa.employee (no, birthday, first_name, last_name, mail_address, sex, department_code, version) FROM stdin;
3	\N	花子	石井	\N	female	\N	1
4	\N	四郎	石井	shiro.ishii@test.com	male	\N	1
5	\N	五郎	石井	\N	male	\N	1
2	1985-02-10	一郎	鈴木	ichiro.suzuki@test.co.jp	male	1	1
1	1980-01-31	太郎	山田	taro.yamada@test.co.jp	male	1	1
\.
