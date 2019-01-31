package com.tim11.demo.Repositories;

import java.sql.Blob;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.Opisfajl;

@Repository
public interface OpisfajlRepositori extends CrudRepository<Opisfajl, Integer>{
	@Query(value="INSERT INTO Opisfajl (id_fajl,opis,datum) VALUES(?1,?2,SYSDATE)", nativeQuery = true)
	public String addOpisFajl( Integer id_fajl, String opis); 
	
	@Query(value="SELECT opis FROM Opisfajl WHERE id_fajl = ?1", nativeQuery = true)
	public String getOpisFromFile(Integer id_fajl);

}
