package com.tim11.demo.Controlers;


import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim11.demo.Entities.Video;
import com.tim11.demo.Services.OpisVideaService;
import com.tim11.demo.Services.OpisfajlService;
import com.tim11.demo.Services.VideoService;

@RestController
@RequestMapping("/video")
public class VideoControler {
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private OpisVideaService ovs;
	
	
	@RequestMapping(value = "/getVideo", method = RequestMethod.GET)
	public Iterable<Video> getAllItems(){
	return videoService.getAllItems();
	}
	@RequestMapping(value = "/getVideo/{id}", method = RequestMethod.GET)
	public java.util.Optional<Video>getVideoByID(@PathVariable Integer id){
		return videoService.getVideoById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteVideo/{id}")
	public String delete(@PathVariable Integer id) {
		return videoService.deleteVideo(id);
}
	@RequestMapping(value="/addVideo", method = RequestMethod.POST)
	public String addVideo( @RequestParam(name="naziv") String naziv, @RequestParam("opisVideo") String opis, @RequestParam("video") MultipartFile video) {
		System.out.println("We're inside");
		Blob blob;
		try {
			byte[] bytes = video.getBytes();
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			 videoService.addVideo(naziv, blob);
			 Integer id_video = videoService.getIDVideo(naziv, 61);
			 System.out.println( id_video);
			 ovs.addOpisVideo(id_video, opis);
			 return "success";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
		}
		return "failed";
		
		   
	}
	@RequestMapping(value = "/getVideoByNaziv", method = RequestMethod.GET)
	public String getSlikeByNaziv(@RequestParam("nazivVideo") String name){
		Iterable<Video> a =  videoService.getVideoByNaziv(name);
		String lista ="Lista: ";
		for(Video i: a) {
			lista = lista + "<div> " + "<b>Naziv:</b> "  + i.getNaziv();
			//i.getKorisnik.getId()
			Integer id_video = videoService.getIDVideo(i.getNaziv(), 61);
			String opis = ovs.getOpisFromVideo(id_video);
			lista = lista + "      " + "<b>Opis: </b>" + opis + "   <b>Ekstenzija: </b> .mp4 </div>" ;
		
		}
		return lista;
			
		
	}
}
