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
@Table(name="Slike")
public class Slike {
	@Id
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_korisnik")
	private Korisnici korisnici;
	
	@ManyToOne
	@JoinColumn(name = "id_predmet")
	private Predmet predmet;
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public Blob getSlika() {
		return slika;
	}
	
	public void setSlika(Blob slika) {
		this.slika = slika;
	}
	
	public Integer getId() {
		return id;
	}
	
	/*@Column(name="id_korisnik")
	private Integer id_korisnik;
	
	@Column(name="id_predmet")
	private Integer id_predmet;*/
	public Slike() {}
	
	public Slike(String naziv, Blob slika) {
		//this.korisnici = korisnici;
		//this.predmet = predmet;
		this.naziv = naziv;
		this.slika = slika;
	}
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="slika")
	private Blob slika;
}
