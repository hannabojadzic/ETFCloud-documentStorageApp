package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//model tekst materijala za izvjestaje(MongoDB)
public class  Izvjestajtxt {
	
		@Id
		@Column
		private String _id;
		
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


		public String getId() {
			return _id;
		}

		@Column
		private String naziv;
		
		@Column
		private Integer ciklus;
		
		@Column
		private Integer semestar;
		
		@Column
		private String tekst;

		public String getTekst() {
			return tekst;
		}

		public void setText(String tekst) {
			this.tekst = tekst;
		}
	
		public Izvjestajtxt() {}
		
		public Izvjestajtxt(String naziv,Integer ciklus, Integer semestar, String tekst) {
			//this.id=i;
			this.naziv=naziv;
			this.ciklus=ciklus;
			this.semestar=semestar;
			this.tekst=tekst;
		}

}