package com.tim11.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Labtxt;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Repositories.LabtxtRepositori;

@Service
public class LabService {
	@Autowired
	public LabtxtRepositori labRepositori;
	
	public List<Labtxt> getAllLabs(){
		
		return labRepositori.findAll();
	}
	
	//dodavanje novog tekst materijala
	public String addLab(Labtxt l) {
		
		try {
			labRepositori.save(l);
		}
		catch(Exception e) {
			return "Dodavanje nije uspjelo";
		}
		
		return "Uspjesno dodavanje";
	}
}
