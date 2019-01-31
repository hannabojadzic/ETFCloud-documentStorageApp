package com.tim11.demo.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Services.PredavanjetxtService;

@RestController
@RequestMapping("/predavanja")
public class PredavanjeControler {

	
	@Autowired
	public PredavanjetxtService predavanjeSerivice;
	
	@RequestMapping(value="/getPredavanja", method = RequestMethod.GET)
	//http dohvatanje svih tekst materijala za predavanje
	public List<Predavanjetxt> getAllPredavanja(){
		return predavanjeSerivice.getAllPredavanja();
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/addPredavanje")
	//http dodavanje novog tekst materijala
	public String create(@RequestBody Predavanjetxt p) {
		
		return predavanjeSerivice.addPredavanje(p);
	}
}
