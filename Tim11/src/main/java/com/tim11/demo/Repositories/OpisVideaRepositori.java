package com.tim11.demo.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.OpisVideo;


@Repository
public interface OpisVideaRepositori  extends CrudRepository<OpisVideo, Integer> {
	@Query(value="INSERT INTO Opisvideo (id_video,opis,datum) VALUES(?1,?2,SYSDATE)", nativeQuery = true)
	public String addOpisVideo( Integer id_video, String opis); 
	
	@Query(value="SELECT opis FROM Opisvideo WHERE id_video = ?1", nativeQuery = true)
	public String getOpisFromVideo(Integer id_video);
}
