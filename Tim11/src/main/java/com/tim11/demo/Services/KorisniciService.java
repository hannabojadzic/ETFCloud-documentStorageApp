package com.tim11.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Repositories.KorisniciRepositori;

@Service
public class KorisniciService {

	@Autowired
	private KorisniciRepositori korisniciRepositori;
	
	public Iterable<Korisnici> getAllItems(){
		
		return korisniciRepositori.findAll();
}

	 public String addKorisnik(String Ime,String Prezime,String Username,String Password,Boolean Admin,Boolean enabled,String role) {
	    	
	    	try {
		  	
		    	korisniciRepositori.addKorisnik(Ime,Prezime,Username,Password,Admin,enabled,role);
	    	}
	    	catch(Exception e){
				return e.getMessage();
			}
	    	return "Uspješno dodan korisnik";
	}
	
	public java.util.Optional<Korisnici> getKorisnikById(Integer id) {
		
		return korisniciRepositori.findById(id);
	}

	public String deleteKorisnik(Integer id) {
		try {
			korisniciRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delet successful";
	}
}
