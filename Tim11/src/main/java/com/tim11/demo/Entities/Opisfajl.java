package com.tim11.demo.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="Opisfajl")
//model korisnika za oracle bazu
public class Opisfajl {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "id_fajl")
	private Fajl fajl;
	
	
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