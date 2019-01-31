package com.tim11.demo.Services;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Fajl;
import com.tim11.demo.Repositories.FajlRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FajlService {
	@Autowired
	private FajlRepositori fajlRepositori;
	
	public Iterable<Fajl> getAllItems(){
		return fajlRepositori.findAll();
	}
public java.util.Optional<Fajl> getFajlById(Integer id) {
		System.out.println("In");
		return fajlRepositori.findById(id);
	}
public Iterable<Fajl> getFajlByName(String name) {
	System.out.println("In");
	return fajlRepositori.findByNaziv(name);
}
public String addFajl( String naziv, Blob fajl) {
	String a = null;
	try {
  	
    	 a= fajlRepositori.addFajl( naziv, fajl);
    	System.out.println(a);
	}
	catch(Exception e){
		System.out.println(a);
		return e.getMessage();
	}
	return "Uspješno dodan fajl";
}

public Integer getIDFajl(String naziv, Integer id_korisnik) {
	return fajlRepositori.getIDFajl(naziv, id_korisnik);
}

public Blob getFajl(String naziv, Integer id_korisnik) {
	return fajlRepositori.getFajl(naziv, id_korisnik);
}


	public String deleteFajl(Integer id) {
		try {
			fajlRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
}
