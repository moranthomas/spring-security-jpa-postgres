INSERT INTO USERS ( username, password, enabled) 
VALUES ('thomas', 'password', true);

INSERT INTO USERS ( username, password, enabled) 
VALUES ('merck', 'password', true);

INSERT INTO AUTHORITIES ( username, authority) 
VALUES ('thomas', 'ROLE_PATIENT');

INSERT INTO AUTHORITIES ( username, authority) 
VALUES ('merck', 'ROLE_RESEARCHER');
