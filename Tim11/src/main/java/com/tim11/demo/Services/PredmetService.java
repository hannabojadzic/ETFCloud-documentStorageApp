package com.tim11.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Predmet;
import com.tim11.demo.Repositories.PredmetRepositori;

@Service
public class PredmetService {

	@Autowired
	private PredmetRepositori predmetRepositori;
	
	public Iterable<Predmet> getAllItems(){
		
		return predmetRepositori.findAll();
}
public java.util.Optional<Predmet> getPredmetById(Integer id) {
		
		return predmetRepositori.findById(id);
	}

	public String deletePredmet(Integer id) {
		try {
			predmetRepositori.deleteById(id);
		}
		catch(Exception e) {
			return e.getMessage().toString();
		}
		return "Delete successful";
	}
}
