package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Labtxt {
	@Id
	@Column
	private String _id;
	
	public String GetNaziv() {
		return nazivDokumenta;
	}
	public void SetNaziv(String t) {
		nazivDokumenta = t;
	}
	public Integer GetPredmet(){
		return predmet;
	}
	public void SetPredmet(Integer p) {
		predmet = p;
	}
	public Integer GetBrojLaba(){
		return brojLaba;
	}
	public void SetBrojLaba(Integer p) {
		brojLaba = p;
	}
	public String GetPodatke() {
		return text;
	}
	public void SetPodatke(String t) {
		text = t;
	}
	public String getId() {
		return _id;
	}
	@Column
	private String nazivDokumenta;
	@Column
	private Integer predmet;
	@Column
	private Integer brojLaba;
	@Column
	private String text;
	public Labtxt() {}
	public Labtxt(String nazivDokumenta, Integer predmet, Integer brojLaba, String text) {
		//this._id = i;
		this.nazivDokumenta = nazivDokumenta;
		this.predmet = predmet;
		this.brojLaba = brojLaba;
		this.text = text;
	}
	
}
