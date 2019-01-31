package com.tim11.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Blob;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.tim11.demo.Entities.Fajl;
import org.springframework.stereotype.Repository;
import com.tim11.demo.Entities.Fajl;
@Repository
public interface FajlRepositori extends CrudRepository<Fajl, Integer>{
	@Query(value="INSERT INTO Fajl (id_korisnik,naziv,fajl) VALUES(61,?1,?2)", nativeQuery = true)
	public String addFajl( String naziv, Blob fajl); 
	
	public Iterable<Fajl> findByNaziv(String name);
	
	@Query(value="SELECT id FROM Fajl WHERE naziv = ?1 AND id_korisnik = ?2", nativeQuery = true)
	public Integer getIDFajl( String naziv, Integer id_korisnik);
	
	@Query(value="SELECT fajl FROM Fajl WHERE naziv = ?1 AND id_korisnik = ?2", nativeQuery = true)
	public Blob getFajl( String naziv, Integer id_korisnik);
	


}
