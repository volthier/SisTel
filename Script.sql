--<ScriptOptions statementTerminator=";"/>

ALTER TABLE cadastro_linha DROP PRIMARY KEY;

DROP TABLE cadastro_linha;

CREATE TABLE cadastro_linha (
	id BIGINT NOT NULL,
	n_linha VARCHAR(255) NOT NULL,
	plano VARCHAR(255),
	nlinha VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

