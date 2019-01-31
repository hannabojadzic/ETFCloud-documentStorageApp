package com.tim11.demo.Controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Predmet;
import com.tim11.demo.Services.PredmetService;

@RestController
@RequestMapping("/predmeti")
public class PredmetControler {
	
	@Autowired
	private PredmetService predmetService;
	
	
	@RequestMapping(value = "/getPredmet", method = RequestMethod.GET)
	public Iterable<Predmet> getAllItems(){
		return predmetService.getAllItems();
	}
	@RequestMapping(value = "/getPredmet/{id}", method = RequestMethod.GET)
	public java.util.Optional<Predmet>getPredmetByID(@PathVariable Integer id){
		return predmetService.getPredmetById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deletePredmet/{id}")
	public String delete(@PathVariable Integer id) {
		return predmetService.deletePredmet(id);
}
}
