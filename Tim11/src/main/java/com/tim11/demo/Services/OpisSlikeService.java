package com.tim11.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.OpisSlike;
import com.tim11.demo.Repositories.OpisSlikeRepositori;


@Service
public class OpisSlikeService {
	@Autowired
	private OpisSlikeRepositori opisSlikeRepositori;
	
	public Iterable<OpisSlike> getAllItems(){
		return opisSlikeRepositori.findAll();
	}
	
	
	public String addOpisSlike( Integer id_slike, String opis) {
		String a = null;
		try {
			System.out.println("Unutra");
			System.out.println(id_slike);
			System.out.println(opis);
	    	 a= opisSlikeRepositori.addOpisSlike(id_slike, opis);
	    	System.out.println(a);
		}
		catch(Exception e){
			System.out.println("Odmah error");
			System.out.println(a);
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		return "Uspješno dodan fajl";
	}
	
	public String getOpisFromSlika(Integer id_slike) {
		String a = null;
		try {
		  	
	    	 a= opisSlikeRepositori.getOpisFromSlika(id_slike);
	    	
		}
		catch(Exception e){
			System.out.println(a);
			return e.getMessage();
		}
		return a;
		
	}
	
	
public java.util.Optional<OpisSlike> getOpisslikeById(Integer id) {
		
		return opisSlikeRepositori.findById(id);
	}

	public String deleteOpisslike(Integer id) {
		try {
			opisSlikeRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
}
