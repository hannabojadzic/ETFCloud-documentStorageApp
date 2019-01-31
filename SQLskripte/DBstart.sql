CREATE TABLE Predmet (id number(10) NOT NULL, naziv varchar2(30) NOT NULL UNIQUE, ciklus number(1) NOT NULL, semestar number(1) NOT NULL, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_predmeti;
CREATE OR REPLACE TRIGGER predmeti_on_insert
  BEFORE INSERT ON Predmet
  FOR EACH ROW
BEGIN
  SELECT sekvenca_predmeti.nextval
  INTO :new.id
  FROM dual;
END;

CREATE TABLE Video (id number(10) NOT NULL, id_korisnik number(10) NOT NULL, id_predmet number(10) NOT NULL, naziv varchar2(30) NOT NULL UNIQUE, video blob, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_video;
CREATE OR REPLACE TRIGGER video_on_insert
  BEFORE INSERT ON Video
  FOR EACH ROW
BEGIN
  SELECT sekvenca_video.nextval
  INTO :new.id
  FROM dual;
END;

CREATE TABLE Slike (id number(10) NOT NULL, id_korisnik number(10) NOT NULL, id_predmet number(10) NOT NULL, naziv varchar2(30) NOT NULL UNIQUE, slika blob, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_slika;
CREATE OR REPLACE TRIGGER slika_on_insert
  BEFORE INSERT ON Slike
  FOR EACH ROW
BEGIN
  SELECT sekvenca_slika.nextval
  INTO :new.id
  FROM dual;
END;

CREATE TABLE Korisnici (id number(10) NOT NULL, Ime varchar2(30) NOT NULL, Prezime varchar2(30) NOT NULL, username varchar2(30) NOT NULL UNIQUE, "password(hash)" varchar2(255) NOT NULL, admin number(1) DEFAULT 0 NOT NULL, PRIMARY KEY (id));
CREATE SEQUENCE sekvenca_korisnika;
CREATE OR REPLACE TRIGGER korisnik_on_insert
  BEFORE INSERT ON Korisnici
  FOR EACH ROW
BEGIN
  SELECT sekvenca_korisnika.nextval
  INTO :new.id
  FROM dual;
END;

ALTER TABLE Video ADD CONSTRAINT FKVideo532852 FOREIGN KEY (id_korisnik) REFERENCES Korisnici (id);
ALTER TABLE Slike ADD CONSTRAINT FKSlike856640 FOREIGN KEY (id_korisnik) REFERENCES Korisnici (id);
ALTER TABLE Video ADD CONSTRAINT FKVideo771438 FOREIGN KEY (id_predmet) REFERENCES Predmet (id);
ALTER TABLE Slike ADD CONSTRAINT FKSlike447650 FOREIGN KEY (id_predmet) REFERENCES Predmet (id);

