package com.tim11.demo.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.OpisSlike;
@Repository
public interface OpisSlikeRepositori   extends CrudRepository<OpisSlike, Integer>{
	@Query(value="INSERT INTO Opisslike (id_slike,opis,datum) VALUES(?1,?2,SYSDATE)", nativeQuery = true)
	public String addOpisSlike( Integer id_slike, String opis); 
	
	@Query(value="SELECT opis FROM Opisslike WHERE id_slike = ?1", nativeQuery = true)
	public String getOpisFromSlika(Integer id_slike);

}
