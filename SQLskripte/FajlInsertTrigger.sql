CREATE TABLE Fajl (id number(10) NOT NULL, id_korisnik number(10) NOT NULL, naziv varchar2(30) NOT NULL, fajl blob, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_fajl;
CREATE OR REPLACE TRIGGER fajl_on_insert
  BEFORE INSERT ON Fajl
  FOR EACH ROW
DECLARE
  var NUMBER;
BEGIN
	SELECT sekvenca_fajl.nextval
	INTO :new.id
	FROM dual;
	SELECT COUNT(*) INTO var FROM Fajl WHERE id_korisnik = :new.id_korisnik AND naziv = :new.naziv;
	IF var > 0
	THEN
		RAISE_APPLICATION_ERROR(-20000, 'Not inserted');
	END IF;
END;

CREATE UNIQUE INDEX Uniqueindexfajl on Fajl(id_korisnik,naziv);
CREATE TABLE Opisfajl (id number(10) NOT NULL, id_fajl number(10) NOT NULL UNIQUE, opis varchar2(30),datum DATE, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_opisfajl;
CREATE OR REPLACE TRIGGER opisfajl_on_insert
  BEFORE INSERT ON Opisfajl
  FOR EACH ROW
BEGIN
  SELECT sekvenca_opisfajl.nextval
  INTO :new.id
  FROM dual;
END;

ALTER TABLE Fajl ADD CONSTRAINT FKFajl542852 FOREIGN KEY (id_korisnik) REFERENCES Korisnici (id);
ALTER TABLE Opisfajl ADD CONSTRAINT FKOpisfajl552852 FOREIGN KEY (id_fajl) REFERENCES Fajl (id);


