CREATE TABLE users
(
  user_name character varying(50) NOT NULL,
  "password" character varying(50) NOT NULL,
  active boolean NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (user_name)
);

CREATE TABLE authorities
(
  user_name character varying(50) NOT NULL,
  authority character varying(50) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (user_name)
      REFERENCES users (user_name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);