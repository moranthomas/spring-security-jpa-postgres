CREATE TABLE users
(
  id serial PRIMARY KEY,
  active boolean NOT NULL,
  user_name character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  roles character varying(50) NOT null
);
