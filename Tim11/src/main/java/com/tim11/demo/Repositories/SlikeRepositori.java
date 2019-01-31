package com.tim11.demo.Repositories;
import java.sql.Blob;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.Slike;

@Repository
public interface SlikeRepositori extends CrudRepository<Slike, Integer>{
	@Query(value="INSERT INTO Slike (id_korisnik, id_predmet,naziv,slika) VALUES(61,37,?1,?2)", nativeQuery = true)
	public String addSlika( String naziv, Blob slika); 
	
	public Iterable<Slike> findByNaziv(String name);
	
	@Query(value="SELECT id FROM Slike WHERE naziv = ?1 AND id_korisnik = ?2", nativeQuery = true)
	public Integer getIDSlike( String naziv, Integer id_korisnik);
}
