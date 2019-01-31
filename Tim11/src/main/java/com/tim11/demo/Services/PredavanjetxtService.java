package com.tim11.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Repositories.predavanjetxtRepositori;

@Service
public class PredavanjetxtService {

	@Autowired
	public predavanjetxtRepositori predavanjeRepositori;
	
	//dohvatanje svih tekst materijala za predavanje
	public List<Predavanjetxt> getAllPredavanja(){
		
		return predavanjeRepositori.findAll();
	}
	
	//dodavanje novog tekst materijala
	public String addPredavanje(Predavanjetxt p) {
		
		try {
			predavanjeRepositori.save(p);
		}
		catch(Exception e) {
			return "Dodavanje nije uspjelo";
		}
		
		return "Uspjesno dodavanje";
	}

}
