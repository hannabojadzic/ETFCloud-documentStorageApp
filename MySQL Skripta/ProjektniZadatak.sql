create table Korisnik(id INT not null auto_increment, ime varchar(50), primary key(id));

select * from korisnik;


create table Privilegije(id INT not null auto_increment, id_korisnika INT not null, brojTabele INT,
r boolean, w boolean, d boolean, primary key (id), foreign key (id_korisnika) references Korisnik(id));

create table Tabela1(id INT not null auto_increment, id_korisnika INT not null, 
zapis varchar(20), primary key(id), foreign key(id_korisnika) references Korisnik(id));

create table Tabela2(id INT not null auto_increment, id_korisnika INT not null, 
vrijednost INT, primary key(id), foreign key(id_korisnika) references Korisnik(id));

create table Tabela3(id INT not null auto_increment, id_korisnika INT not null, 
zapis varchar(25), primary key(id), foreign key(id_korisnika) references Korisnik(id));

create table LogTabela(id INT not null auto_increment, imeKorisnika varchar(50), 
brojTabele INT, r boolean, w boolean, d boolean, datum datetime, primary key (id));




drop trigger InsertTabela1;
drop trigger InsertTabela2;
drop trigger InsertTabela3;
delimiter //
create trigger InsertTabela1 before insert on Tabela1
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 1;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela1(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 1, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;

drop trigger UpdateTabela1;
delimiter //
create trigger UpdateTabela1 before update on Tabela1
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 1;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela1(new.id, new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 1, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;

drop procedure PInsertTabela1;
delimiter //
create procedure PInsertTabela1 ( in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where id_korisnikap = Privilegije.id_korisnika and Privilegije.brojTabele = 1;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 1, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;





drop trigger InsertTabela2;
delimiter //
create trigger InsertTabela2 before insert on Tabela2
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 2;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela2(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 2, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;


drop trigger UpdateTabela2;
delimiter //
create trigger UpdateTabela2 before update on Tabela2
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 2;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela2(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 2, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;

drop procedure PInsertTabela2;
delimiter //
create procedure PInsertTabela2 (in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where id_korisnikap = Privilegije.id_korisnika and Privilegije.brojTabele = 2;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 2, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;


drop trigger InsertTabela3;
delimiter //
create trigger InsertTabela3 before insert on Tabela3
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 3;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela3(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 3, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;


drop trigger UpdateTabela3;
delimiter //
create trigger UpdateTabela3 before update on Tabela3
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where new.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 3;
    select ime into imep from Korisnik where new.id_korisnika = Korisnik.id;
    call PInsertTabela3(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, ime, 3, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;

drop procedure PInsertTabela3;
delimiter //
create procedure PInsertTabela3 (in idp int, in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where idp = Privilegije.id_korisnika and Privilegije.brojTabele = 3;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    call PInsertTabela3(new.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 3, false, true, false, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju upisa';
    end if;
end;



drop trigger DeleteTabela1;
drop trigger DeleteTabela2;
drop trigger DeleteTabela3;
delimiter //
create trigger DeleteTabela1 before delete on Tabela1
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    call PDeleteTabela1(old.id_korisnika);
    select w into varijabla from Privilegije where old.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 1;
    select ime into imep from Korisnik where old.id_korisnika = Korisnik.id;
    
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 1, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;



drop procedure PDeleteTabela1;
delimiter //
create procedure PDeleteTabela1 ( in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where id_korisnikap = Privilegije.id_korisnika and Privilegije.brojTabele = 1;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 1, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;




drop trigger DeleteTabela2;
delimiter //
create trigger DeleteTabela2 before delete on Tabela2
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where old.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 2;
    select ime into imep from Korisnik where old.id_korisnika = Korisnik.id;
    call PDeleteTabela2(old.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 2, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;

drop procedure PDeleteTabela2;
delimiter //
create procedure PDeleteTabela2 ( in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where id_korisnikap = Privilegije.id_korisnika and Privilegije.brojTabele = 2;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 2, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;



drop trigger DeleteTabela3;
delimiter //
create trigger DeleteTabela3 before delete on Tabela3
for each row
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where old.id_korisnika = Privilegije.id_korisnika and Privilegije.brojTabele = 3;
    select ime into imep from Korisnik where old.id_korisnika = Korisnik.id;
    call PDeleteTabela3(old.id_korisnika);
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 3, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;


drop procedure PDeleteTabela3;
delimiter //
create procedure PDeleteTabela3 ( in id_korisnikap int)
begin
	declare varijabla boolean;
    declare imep varchar(50);
    select w into varijabla from Privilegije where id_korisnikap = Privilegije.id_korisnika and Privilegije.brojTabele = 3;
    select ime into imep from Korisnik where id_korisnikap = Korisnik.id;
    if varijabla = TRUE then
		insert into LogTabela values (NULL, imep, 3, false, false, true, sysdate());
	else 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Korisnik nema privilegiju brisanja';
    end if;
end;



select * from korisnik;
select * from privilegije;
insert into Privilegije values (NULL, 1, 3, TRUE, TRUE, TRUE);
select * from Tabela1;
insert into Tabela1 values (NULL, 1, 'nesto2');
insert into Tabela2 values (NULL, 1, 5);


select * from LogTabela;
