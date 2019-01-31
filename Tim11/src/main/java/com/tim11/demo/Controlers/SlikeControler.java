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

import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Entities.Slike;
import com.tim11.demo.Services.OpisSlikeService;
import com.tim11.demo.Services.OpisfajlService;
import com.tim11.demo.Services.SlikeService;

@RestController
@RequestMapping("/slike")
public class SlikeControler {
	@Autowired
	private SlikeService slikeService;
	@Autowired
	private OpisSlikeService oss;
	
	@RequestMapping(value = "/getSlike", method = RequestMethod.GET)
	public Iterable<Slike> getAllItems(){
	return slikeService.getAllItems();
	}
	@RequestMapping(value = "/getSlike/{id}", method = RequestMethod.GET)
	public java.util.Optional<Slike>getSlikeByID(@PathVariable Integer id){
		return slikeService.getSlikeById(id);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteSlika/{id}")
	public String delete(@PathVariable Integer id) {
		return slikeService.deleteSlika(id);
}
	@RequestMapping(value="/addSlika", method = RequestMethod.POST)
	public String addSlika( @RequestParam(name="naziv") String naziv, @RequestParam("opisSlike") String opis, @RequestParam("slika") MultipartFile slika) {
		System.out.println("We're inside");
		Blob blob;
		try {
			byte[] bytes = slika.getBytes();
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			 slikeService.addSlika(naziv, blob);
			 Integer id_slike = slikeService.getIDSlike(naziv, 61);
			 System.out.println( "Dobijeni ID slike je: ");
			 System.out.println( id_slike);
			 oss.addOpisSlike(id_slike, opis);
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
	
	@RequestMapping(value = "/getSlikeByNaziv", method = RequestMethod.GET)
	public String getSlikeByNaziv(@RequestParam("nazivSlike") String name){
		Iterable<Slike> a =  slikeService.getSlikeByNaziv(name);
		String lista ="Lista: ";
		System.out.println( "lista");
		for(Slike i: a) {
			lista = lista + "<div> " + "<b>Naziv:</b> "  + i.getNaziv();
			//i.getKorisnik.getId()
			Integer id_slike = slikeService.getIDSlike(i.getNaziv(), 61);
			String opis = oss.getOpisFromSlika(id_slike);
			lista = lista + "      " + "<b>Opis: </b>" + opis + "   <b>Ekstenzija: </b> .jpg </div>" ;
			System.out.println( lista);
		}
		return lista;
			
		
	}
	
}
