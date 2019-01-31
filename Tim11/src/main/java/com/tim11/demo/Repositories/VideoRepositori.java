package com.tim11.demo.Repositories;

import java.sql.Blob;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.Video;


@Repository
public interface VideoRepositori extends CrudRepository<Video, Integer> {
	@Query(value="INSERT INTO Video (id_korisnik, id_predmet,naziv,video) VALUES(61,37,?1,?2)", nativeQuery = true)
	public String addVideo( String naziv, Blob video); 
	
	public Iterable<Video> findByNaziv(String name);
	
	@Query(value="SELECT id FROM Video WHERE naziv = ?1 AND id_korisnik = ?2", nativeQuery = true)
	public Integer getIDVideo( String naziv, Integer id_korisnik);
}