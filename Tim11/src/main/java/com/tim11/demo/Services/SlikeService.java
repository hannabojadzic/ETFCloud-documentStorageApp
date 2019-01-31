package com.tim11.demo.Services;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Entities.Slike;
import com.tim11.demo.Repositories.SlikeRepositori;

@Service
public class SlikeService {
	@Autowired
	private SlikeRepositori slikeRepositori;
	
	public Iterable<Slike> getAllItems(){
		return slikeRepositori.findAll();
	}
public java.util.Optional<Slike> getSlikeById(Integer id) {
		
		return slikeRepositori.findById(id);
	}

	public String deleteSlika(Integer id) {
		try {
			slikeRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
	public Iterable<Slike> getSlikeByNaziv(String Name){
		return slikeRepositori.findByNaziv(Name);
	}
public String addSlika( String naziv, Blob slika) {
		
		try {
	  	
	    	slikeRepositori.addSlika( naziv, slika);
		}
		catch(Exception e){
			return e.getMessage();
		}
		return "Uspješno dodana slika";
	}
public Integer getIDSlike(String naziv, Integer id_korisnik) {
	return slikeRepositori.getIDSlike(naziv, id_korisnik);
}
}
