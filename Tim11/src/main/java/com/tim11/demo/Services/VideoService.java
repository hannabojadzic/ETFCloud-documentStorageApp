package com.tim11.demo.Services;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Video;
import com.tim11.demo.Repositories.VideoRepositori;

@Service
public class VideoService {

	@Autowired
	private VideoRepositori videoRepositori;
	
	public Iterable<Video> getAllItems(){
		
		return videoRepositori.findAll();
}
public java.util.Optional<Video> getVideoById(Integer id) {
		
		return videoRepositori.findById(id);
	}

	public String deleteVideo(Integer id) {
		try {
			videoRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
public String addVideo( String naziv, Blob video) {
		
		try {
	  	
	    	videoRepositori.addVideo( naziv, video);
		}
		catch(Exception e){
			return e.getMessage();
		}
		return "Uspješno dodan video";
	}
	public Iterable<Video> getVideoByNaziv(String Name){
		return videoRepositori.findByNaziv(Name);
	}
	public Integer getIDVideo(String naziv, Integer id_korisnik) {
		return videoRepositori.getIDVideo(naziv, id_korisnik);
	}
}
