package com.tim11.demo.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.OpisSlike;
import com.tim11.demo.Services.OpisSlikeService;


@RestController
@RequestMapping("/slike")
public class OpisSlikaControler {
	@Autowired
	private OpisSlikeService opisSlikeService;
	
	@RequestMapping(value = "/getOpisslike", method = RequestMethod.GET)
	public Iterable<OpisSlike> getAllItems(){
	return opisSlikeService.getAllItems();
	}
	@RequestMapping(value = "/getOpisslike/{id}", method = RequestMethod.GET)
	public java.util.Optional<OpisSlike>getOpisslikeByID(@PathVariable Integer id){
		return opisSlikeService.getOpisslikeById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteOpisslike/{id}")
	public String delete(@PathVariable Integer id) {
		return opisSlikeService.deleteOpisslike(id);
}
}
