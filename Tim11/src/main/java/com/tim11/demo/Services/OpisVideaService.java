package com.tim11.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.OpisVideo;
import com.tim11.demo.Repositories.OpisVideaRepositori;


@Service
public class OpisVideaService {
	@Autowired
	private OpisVideaRepositori opisvideaRepositori;
	
	public Iterable<OpisVideo> getAllItems(){
		return opisvideaRepositori.findAll();
	}
	
	
	public String addOpisVideo( Integer id_video, String opis) {
		String a = null;
		try {
	  	
	    	 a= opisvideaRepositori.addOpisVideo(id_video, opis);
	    	System.out.println(a);
		}
		catch(Exception e){
			System.out.println(a);
			return e.getMessage();
		}
		return "Uspješno dodan video";
	}
	
	public String getOpisFromVideo(Integer id_video) {
		String a = null;
		try {
		  	
	    	 a= opisvideaRepositori.getOpisFromVideo(id_video);
	    	
		}
		catch(Exception e){
			System.out.println(a);
			return e.getMessage();
		}
		return a;
		
	}
	
	
public java.util.Optional<OpisVideo> getOpisvideoById(Integer id) {
		
		return opisvideaRepositori.findById(id);
	}

	public String deleteOpisvideo(Integer id) {
		try {
			opisvideaRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
}
