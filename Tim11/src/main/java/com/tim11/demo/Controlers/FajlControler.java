package com.tim11.demo.Controlers;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim11.demo.Entities.Korisnici;

import com.tim11.demo.Entities.Fajl;
import com.tim11.demo.Services.FajlService;
import com.tim11.demo.Entities.Opisfajl;
import com.tim11.demo.Services.OpisfajlService;

import antlr.Utils;

@RestController
@RequestMapping("/fajl")
public class FajlControler {
	@Autowired
	private FajlService fajlService;
	@Autowired
	private OpisfajlService ofs;
	
	@RequestMapping(value = "/getFajl", method = RequestMethod.GET)
	public Iterable<Fajl> getAllItems(){
	return fajlService.getAllItems();
	}
	
	@RequestMapping(value = "/getFajlByNaziv", method = RequestMethod.GET)
	public  String getFajlByName(@RequestParam("nazivFajl") String name){
		System.out.println(name);
		Iterable<Fajl> a =  fajlService.getFajlByName(name);
		
		
		
		String lista = "";
		for (Fajl i: a) {
			lista = lista + "<div> " + "<b>Naziv:</b> "  + i.getNaziv();
			//i.getKorisnik.getId()
			Integer id_fajla = fajlService.getIDFajl(i.getNaziv(), 61);
			String opis = ofs.getOpisFromFile(id_fajla);
			lista = lista + "      " + "<b>Opis: </b>" + opis + "   <b>Ekstenzija: </b> .pdf </div>" ;
		}
		
		return lista;
			
		
	}
	
	@RequestMapping(value="/downloadFile", method = RequestMethod.GET) 
	public ResponseEntity<Resource> downloadFile(@RequestParam("nazivFajl") String name) throws SQLException {
		System.out.println("IIN");
		Blob b = fajlService.getFajl(name, 61);
		int blobLength = (int) b.length();  
		byte[] blobAsBytes = b.getBytes(1, blobLength);
		System.out.println(blobAsBytes.length);
	
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType( "application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + ".pdf" + "\"")
                .body(new ByteArrayResource(blobAsBytes));
	}
	
	@RequestMapping(value = "/getFajl/{id}", method = RequestMethod.GET)
	public java.util.Optional<Fajl>getFajlByID(@PathVariable Integer id){
		return fajlService.getFajlById(id);
	}
	
	
	@RequestMapping(value="/DodajDatoteku", method = RequestMethod.POST)
	public String addFajl( @RequestParam("nazivDatoteka") String naziv,@RequestParam("opisDatoteke") String opis, @RequestParam("inputDatoteke") MultipartFile fajl) {
		System.out.println("We're inside");
		Blob blob;
		try {
			byte[] bytes = fajl.getBytes();
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			 fajlService.addFajl(naziv, blob);
			 Integer id_fajla = fajlService.getIDFajl(naziv, 61);
			 System.out.println( id_fajla);
			 ofs.addOpisFajl(id_fajla, opis);
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
	
	@RequestMapping(method= RequestMethod.DELETE, value="/deleteFajl/{id}")
	public String delete(@PathVariable Integer id) {
		return fajlService.deleteFajl(id);
}
}
