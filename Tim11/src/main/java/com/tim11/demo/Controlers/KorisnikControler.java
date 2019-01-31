package com.tim11.demo.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Services.KorisniciService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "user", description = "Operations regarding the user of the API.")
public class KorisnikControler {

	@Autowired
	private KorisniciService korisniciService;
	
	
	@RequestMapping(value="/korisnici", method = RequestMethod.GET)
	public Iterable<Korisnici> getAllItems(){
		return korisniciService.getAllItems();
		
	}
	
	@RequestMapping(value = "/getKorisnik/{id}", method = RequestMethod.GET)
	public java.util.Optional<Korisnici>getKorisnikByID(@PathVariable Integer id){
		return korisniciService.getKorisnikById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteKorisnik/{id}")
	public String delete(@PathVariable Integer id) {
		return korisniciService.deleteKorisnik(id);
	}
	
	
}
