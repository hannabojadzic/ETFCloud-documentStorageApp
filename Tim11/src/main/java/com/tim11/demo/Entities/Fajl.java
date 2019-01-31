package com.tim11.demo.Entities;


import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Fajl")
public class Fajl {
	@Id
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_korisnik")
	private Korisnici korisnici;
	
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public Blob getFajl() {
		return fajl;
	}
	
	public void setFajl(Blob slika) {
		this.fajl = slika;
	}
	
	public Integer getId() {
		return id;
	}
	public Fajl() {}
	
	public Fajl( String naziv, Blob fajl) {
		this.naziv= naziv;
		this.fajl= fajl;
	}

	
	/*@Column(name="id_korisnik")
	private Integer id_korisnik;
	
	@Column(name="id_predmet")
	private Integer id_predmet;*/
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="fajl")
	private Blob fajl;
}
