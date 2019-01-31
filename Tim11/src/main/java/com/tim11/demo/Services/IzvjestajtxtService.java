package com.tim11.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim11.demo.Entities.Izvjestajtxt;
import com.tim11.demo.Repositories.izvjestajtxtRepositori;

@Service
public class IzvjestajtxtService {

	@Autowired
	public izvjestajtxtRepositori izvjestajRepositori;
	
	//dohvatanje svih tekst materijala za predavanje
	public List<Izvjestajtxt> getAllIzvjestaj(){
		
		return izvjestajRepositori.findAll();
	}
	
	//dodavanje novog tekst materijala
	public String addIzvjestaj(Izvjestajtxt p) {
		
		try {
			izvjestajRepositori.save(p);
		}
		catch(Exception e) {
			return "Dodavanje nije uspjelo";
		}
		
		return "Uspjesno dodavanje";
	}

}
