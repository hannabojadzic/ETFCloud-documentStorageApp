package com.tim11.demo.Services;


import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Opisfajl;
import com.tim11.demo.Repositories.OpisfajlRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OpisfajlService {
	@Autowired
	private OpisfajlRepositori opisfajlRepositori;
	
	public Iterable<Opisfajl> getAllItems(){
		return opisfajlRepositori.findAll();
	}
	
	
	public String addOpisFajl( Integer id_file, String opis) {
		String a = null;
		try {
	  	
	    	 a= opisfajlRepositori.addOpisFajl(id_file, opis);
	    	System.out.println(a);
		}
		catch(Exception e){
			System.out.println(a);
			return e.getMessage();
		}
		return "Uspješno dodan fajl";
	}
	
	public String getOpisFromFile(Integer id_fajl) {
		String a = null;
		try {
		  	
	    	 a= opisfajlRepositori.getOpisFromFile(id_fajl);
	    	
		}
		catch(Exception e){
			System.out.println(a);
			return e.getMessage();
		}
		return a;
		
	}
	
	
public java.util.Optional<Opisfajl> getOpisfajlById(Integer id) {
		
		return opisfajlRepositori.findById(id);
	}

	public String deleteOpisfajl(Integer id) {
		try {
			opisfajlRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
}
