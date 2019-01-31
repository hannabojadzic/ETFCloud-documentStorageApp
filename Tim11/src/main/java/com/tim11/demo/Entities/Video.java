package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Blob;

import javax.persistence.*;
import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Predmet;
@Entity
@Table(name="Video")
public class Video {
	
	
	
	@Id
	@Column(name="id")
	private Integer id;
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public Blob getVideo() {
		return video;
	}

	public void setVideo( Blob data) {
		this.video = data;
	}
	

	public Integer getId() {
		return id;
	}
	public Korisnici getKorisnik()  { 
		return id_korisnik;
	}
	public Predmet getPredmet()  { 
		return id_predmet;
	}
	

	@ManyToOne
	@JoinColumn(name= "id_korisnik")
	private Korisnici id_korisnik;
	@ManyToOne
	@JoinColumn(name= "id_predmet")
	private Predmet id_predmet;
	@Column(name="naziv")
	private String naziv;
	@Lob
	@Column(name="video")
	private Blob video;
	
	public Video(){}
	
	public Video(String naziv, Blob video){
		this.naziv = naziv;
		this.video = video;
	}
	
	

}