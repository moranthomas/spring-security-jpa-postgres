# spring-security-jpa-postgres

## Requires Postgres to be running

Check that postgres got installed correctly by checking the version:
$ postgres -V
(on windows just use the postgresSQL shell that comes with the installer) 

## Create a new 'postgres' user
If you get an error saying the postgres user doesn’t exist then create that user:

$ /usr/local/opt/postgres/bin/createuser -s postgres

## Create a new database and grant access to a user on it:
postgres=# CREATE DATABASE security_demo;
postgres=# create user realli with encrypted password 'password';
postgres=# grant all privileges on database security_demo to postgres;
