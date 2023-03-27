
--
-- Structure for table vaultapi_user
--

DROP TABLE IF EXISTS vaultapi_user;
CREATE TABLE vaultapi_user (
id_user int AUTO_INCREMENT,
firstname varchar(50) default '' NOT NULL,
lastname varchar(50) default '' NOT NULL,
password varchar(50) default '' NOT NULL,
email varchar(255) default '' NOT NULL,
birthdate date NOT NULL,
firm varchar(50) default '' NOT NULL,
PRIMARY KEY (id_user)
);
