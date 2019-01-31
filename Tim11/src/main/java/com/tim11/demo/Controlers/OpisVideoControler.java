package com.tim11.demo.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim11.demo.Entities.OpisVideo;
import com.tim11.demo.Entities.Opisfajl;
import com.tim11.demo.Services.OpisVideaService;


@RestController
@RequestMapping("/video")
public class OpisVideoControler {
	@Autowired
	private OpisVideaService opisvideaService;
	@RequestMapping(value = "/getOpisvideo", method = RequestMethod.GET)
	public Iterable<OpisVideo> getAllItems(){
	return opisvideaService.getAllItems();
	}
	@RequestMapping(value = "/getOpisvideo/{id}", method = RequestMethod.GET)
	public java.util.Optional<OpisVideo>getOpisfajlByID(@PathVariable Integer id){
		return opisvideaService.getOpisvideoById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteOpisvideo/{id}")
	public String delete(@PathVariable Integer id) {
		return opisvideaService.deleteOpisvideo(id);
	}
}
