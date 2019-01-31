package com.tim11.demo.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Opisslike")
public class OpisSlike {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "id_slike")
	private Slike slika;
	
	
	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String ime) {
		this.opis = ime;
	}


	public Integer getId() {
		return id;
	}
	@Column(name="opis")
	private String opis;
	
	
	
	@Column(name="datum")
	private Date datum;
	
	
}
