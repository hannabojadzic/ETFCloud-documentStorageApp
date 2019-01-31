package com.tim11.demo.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.Fajl;
import com.tim11.demo.Entities.Opisfajl;
import com.tim11.demo.Services.OpisfajlService;

@RestController
@RequestMapping("/fajl")
public class OpisfajlControler {
	@Autowired
	private OpisfajlService opisfajlService;
	
	@RequestMapping(value = "/getOpisfajl", method = RequestMethod.GET)
	public Iterable<Opisfajl> getAllItems(){
	return opisfajlService.getAllItems();
	}
	@RequestMapping(value = "/getOpisfajl/{id}", method = RequestMethod.GET)
	public java.util.Optional<Opisfajl>getOpisfajlByID(@PathVariable Integer id){
		return opisfajlService.getOpisfajlById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteOpisfajl/{id}")
	public String delete(@PathVariable Integer id) {
		return opisfajlService.deleteOpisfajl(id);
}
}
