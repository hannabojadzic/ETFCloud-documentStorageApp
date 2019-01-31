package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Predmet")
public class Predmet {

	@Id
	@Column(name="id")
	private Integer id;
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public Integer getCiklus() {
		return ciklus;
	}

	public void setCiklus(Integer ciklus) {
		this.ciklus = ciklus;
	}
	public Integer getSemestar() {
		return semestar;
	}

	public void setSemestar(Integer semestar) {
		this.semestar = semestar;
	}


	public Integer getId() {
		return id;
	}

	@Column(name="naziv")
	private String naziv;
	
	@Column(name="ciklus")
	private Integer ciklus;
	
	@Column(name="semestar")
	private Integer semestar;
	

}
