package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//model tekst materijala za predavanje(MongoDB)
public class Predavanjetxt {
	
		@Id
		@Column
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

		@Column
		private String naziv;
		
		@Column
		private Integer ciklus;
		
		@Column
		private Integer semestar;
		
		@Column
		private String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	
		public Predavanjetxt() {}
		
		public Predavanjetxt(Integer i,String naziv,Integer ciklus, Integer semestar, String text) {
			this.id=i;
			this.naziv=naziv;
			this.ciklus=ciklus;
			this.semestar=semestar;
			this.text=text;
		}

}
