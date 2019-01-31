package com.tim11.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="Korisnici")
//model korisnika za oracle bazu
public class Korisnici {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordhash(String passwordhash) {
		this.password = passwordhash;
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}
	@Column(name="ime")
	private String ime;
	
	@Column(name="prezime")
	private String prezime;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="admin")
	private Boolean admin;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@Column(name="role")
	private String role;
	
	public Korisnici() {}
	
	public Korisnici(String Ime,String Prezime,String Username,String Password,Boolean Admin,String role) {
		this.ime=Ime;
		this.prezime=Prezime;
		this.password=Password;
		this.admin=Admin;
		this.enabled=true;
		this.role=role;
	}
	
}
