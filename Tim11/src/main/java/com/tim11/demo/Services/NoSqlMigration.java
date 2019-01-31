package com.tim11.demo.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim11.demo.Entities.Izvjestajtxt;
import com.tim11.demo.Entities.Labtxt;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Repositories.LabtxtRepositori;
import com.tim11.demo.Repositories.izvjestajtxtRepositori;
import com.tim11.demo.Repositories.predavanjetxtRepositori;


@Service
public class NoSqlMigration {
	
	
	
	@Autowired
	IzvjestajtxtService Izvjestaj;
	@Autowired
	PredavanjetxtService Predavanje;
	@Autowired
	LabService Lab;
	
	
	
	private static final String DATABASE = "nosqlmigration";
	private static final String URL = "jdbc:mysql://localhost:3306/nosqlmigration?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    Connection connection;

    public NoSqlMigration() {}
    
    public List<com.tim11.demo.Entities.Izvjestajtxt> getAllIzvjestaje() {
    	return  Izvjestaj.getAllIzvjestaj();
    }
    
    public List<com.tim11.demo.Entities.Labtxt> getAllLab() {
    	return Lab.getAllLabs();
    }
    public List<com.tim11.demo.Entities.Predavanjetxt> getAllPredavanjetxt() {
    	return Predavanje.getAllPredavanja();
    }
    
    public void MigrateIzvjestaje() {
    	try {
    		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    		System.out.println("here");
    		connection.setAutoCommit(false);
    		Statement statement = connection.createStatement();
    		String query="CREATE TABLE IF NOT EXISTS `nosqlmigration`.`Izvjestaji` ( `id` VARCHAR(30) NOT NULL , `naziv` VARCHAR(30) NOT NULL , `ciklus` INT(1) NOT NULL , `semestar` INT(1) NOT NULL , `text` TEXT NOT NULL )";
    		statement.executeUpdate(query);
    		
    		List<Izvjestajtxt> Izvjestaji = getAllIzvjestaje();
    		
    		for(Izvjestajtxt izvjestaj: Izvjestaji) {
    			query="INSERT INTO `Izvjestaji` (`id`, `naziv`, `ciklus`, `semestar`, `text`) VALUES (?,?,?,?,?)";    			
    			PreparedStatement pstatement = connection.prepareStatement(query);
    			pstatement.setObject(1, izvjestaj.getId());
    			pstatement.setObject(2, izvjestaj.getNaziv());
    			pstatement.setObject(3, izvjestaj.getCiklus());
    			pstatement.setObject(4, izvjestaj.getSemestar());
    			pstatement.setObject(5, izvjestaj.getTekst());
    			pstatement.executeUpdate();
    		}
    		
    		connection.commit();
    	
    	}
    	catch(Exception e) {
    		try {
    			System.out.println(e.getMessage());
    			connection.rollback();
    		
    			connection.close();
    		}
    		catch(Exception e1){
    			e1.printStackTrace();
    		}
    	}
    	
    }
    public void MigrateLab() {
    	try {
    		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    		System.out.println("here");
    		connection.setAutoCommit(false);
    		Statement statement = connection.createStatement();
    		String query="CREATE TABLE IF NOT EXISTS `nosqlmigration`.`Lab` ( `id` VARCHAR(30) NOT NULL , `nazivDokumenta` VARCHAR(30) NOT NULL , `predmet` INT(3) NOT NULL , `brojLaba` INT(3) NOT NULL , `text` TEXT NOT NULL )";
    		statement.executeUpdate(query);
    		
    		List<Labtxt> Labovi = getAllLab();
    		
    		for(Labtxt lab: Labovi) {
    			query="INSERT INTO `Lab` (`id`, `nazivDokumenta`, `predmet`, `brojLaba`, `text`) VALUES (?,?,?,?,?)";    			
    			PreparedStatement pstatement = connection.prepareStatement(query);
    			pstatement.setObject(1, lab.getId());
    			pstatement.setObject(2, lab.GetNaziv());
    			pstatement.setObject(3, lab.GetPredmet());
    			pstatement.setObject(4, lab.GetBrojLaba());
    			pstatement.setObject(5, lab.GetPodatke());
    			pstatement.executeUpdate();
    		}
    		connection.commit();
    	}
    	catch(Exception e) {
    		try {
    			connection.rollback();
    			System.out.println(e.getMessage());
    			connection.close();
    		}
    		catch(Exception e1){
    			e1.printStackTrace();
    		}
    	}
    	
    }
    public void MigratePredavanjetxt() {
    	try {
    		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    		System.out.println("here");
    		connection.setAutoCommit(false);
    		Statement statement = connection.createStatement();
    		String query="CREATE TABLE IF NOT EXISTS `nosqlmigration`.`Predavanje` ( `id` VARCHAR(30) NOT NULL , `naziv` VARCHAR(30) NOT NULL , `ciklus` INT(1) NOT NULL , `semestar` INT(1) NOT NULL , `text` TEXT NOT NULL )";
    		statement.executeUpdate(query);
    		
    		List<Predavanjetxt> Predavanja = getAllPredavanjetxt();
    		
    		for(Predavanjetxt predavanje: Predavanja) {
    			query="INSERT INTO `Predavanje` (`id`, `naziv`, `ciklus`, `semestar`, `text`) VALUES (?,?,?,?,?)";    			
    			PreparedStatement pstatement = connection.prepareStatement(query);
    			pstatement.setObject(1, predavanje.getId());
    			pstatement.setObject(2, predavanje.getNaziv());
    			pstatement.setObject(3, predavanje.getCiklus());
    			pstatement.setObject(4, predavanje.getSemestar());
    			pstatement.setObject(5, predavanje.getText());
    			pstatement.executeUpdate();
    		}
    		
    		
    		connection.commit();
    	}
    	catch(Exception e) {
    		try {
    			connection.rollback();
    			System.out.println(e.getMessage());
    			connection.close();
    		}
    		catch(Exception e1){
    			e1.printStackTrace();
    		}
    	}
    	
    }
    public void migrate() {
    	MigratePredavanjetxt();
    	MigrateLab();
    	MigrateIzvjestaje();
    }
}
