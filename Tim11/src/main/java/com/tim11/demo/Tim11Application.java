package com.tim11.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tim11.demo.Services.PredmetService;
import com.tim11.demo.Entities.Predmet;
import com.tim11.demo.Services.SlikeService;
import com.tim11.demo.Entities.Slike;
import com.tim11.demo.Services.KorisniciService;
import com.tim11.demo.Services.LabService;
import com.tim11.demo.Services.NoSqlMigration;
import com.tim11.demo.Entities.Korisnici;
import com.tim11.demo.Entities.Labtxt;
import com.tim11.demo.Entities.Predavanjetxt;
import com.tim11.demo.Services.VideoService;

import oracle.jdbc.datasource.OracleDataSource;

import com.tim11.demo.Entities.Video;
import com.tim11.demo.Repositories.KorisniciRepositori;
import com.tim11.demo.Repositories.LabtxtRepositori;
import com.tim11.demo.Repositories.predavanjetxtRepositori;
import com.tim11.demo.Repositories.izvjestajtxtRepositori;
import com.tim11.demo.Entities.Izvjestajtxt;
import com.tim11.demo.Services.IzvjestajtxtService;
import com.tim11.demo.Entities.Fajl;
import com.tim11.demo.Controlers.FajlControler;
import com.tim11.demo.Services.FajlService;
import com.tim11.demo.Entities.Opisfajl;
import com.tim11.demo.Services.OpisfajlService;
import com.tim11.demo.Services.PredavanjetxtService;
import com.tim11.demo.Services.OpisSlikeService;
@SpringBootApplication
public class Tim11Application implements CommandLineRunner {
	
	@Autowired
		 PredmetService predmetService;
	@Autowired
		 SlikeService slikeService;
	@Autowired
		 KorisniciService korisniciService;
	@Autowired
		VideoService videoService;
	@Autowired
	    KorisniciRepositori korisniciRepositori; 
	@Autowired
		predavanjetxtRepositori predavanjeRepositori;
	@Autowired 
		IzvjestajtxtService izvjestajService;
	@Autowired 
	izvjestajtxtRepositori izvjestajRepositori;
	@Autowired
		LabService labService;
	@Autowired 
		LabtxtRepositori labRepositori;
	@Autowired
		FajlService fajlservice;
	@Autowired
		OpisfajlService opisfajlservice;
	@Autowired
		OpisSlikeService opisslikeservice;
	@Autowired
		FajlControler fajlcontroler;
	@Autowired
		DataSource dataSource;
	@Autowired
		NoSqlMigration nosql;
	@Autowired
		PredavanjetxtService predavanjetxtService;
	
	
	
	
	
	//static Connection connectionMySQ 
	public static void main(String[] args) {
		SpringApplication.run(Tim11Application.class, args);
	}
		 @Override
		    public void run(String... arg0) throws Exception {
		        System.out.println("HEllo");
		        Iterable<Predmet> predmeti = predmetService.getAllItems();
		        for( Predmet p: predmeti)  {
		        	 System.out.println("Introducing predmet => " + p.getNaziv());
		        }
		        //nosql.migrate();
		        //nosql.MigrateLab();
		        //nosql.MigratePredavanjetxt();
		        //System.out.println(nosql.getAllIzvjestaje());
		        //System.out.println(nosql.getAllLab());
		        //System.out.println(nosql.getAllPredavanjetxt());
		        //Predavanjetxt p1=new Predavanjetxt(1,"pradavanje1",1, 1, "test1"); 
		        //Predavanjetxt p2=new Predavanjetxt(2,"pradavanje2",1, 1, "test2");
		        //predavanjetxtService.addPredavanje(p1);
		        //predavanjetxtService.addPredavanje(p2);
		        //Labtxt l=new Labtxt("lab2", 1, 2,"test2");
		        //labService.addLab(l);
		        //Stari testovi servisa:
		        
		        /* Iterable<Predmet> predmeti = predmetService.getAllItems();
		        for( Predmet p: predmeti)  {
		        	 System.out.println("Introducing predmet => " + p.getNaziv());
		        }
		        
		        Iterable<Slike> slike = slikeService.getAllItems();
		        for( Slike s: slike)  {
		        	 System.out.println(s.getNaziv());
		        }
		        System.out.println("Slika sa id 1 je => " + slikeService.getSlikeById(1).get().getNaziv());
		        Iterable<Video> video = videoService.getAllItems();
		        for(  Video s: video)  {
		        	 System.out.println(s.getNaziv());
		        	 System.out.println(s.getPredmet().getNaziv());
		        }
		        
		        
		        System.out.println("Predmet sa id 6 => " + predmetService.getPredmetById(6).get().getNaziv());
		        Iterable<Korisnici> korisnici = korisniciService.getAllItems();
		        for( Korisnici k: korisnici)  {
		        	 System.out.println("Introducing korisnik => " + k.getPrezime());
		        }
		        */
		       //System.out.println(opisslikeservice.addOpisSlike(41,"opiss"));
		    	
		        
		        //Test druge baze:
		        
		        /*for (Izvjestajtxt a: izvjestajService.getAllIzvjestaj()) {
		        	System.out.println("Introducing izvjestaj => " + a.getNaziv());
		        }
		        for (Izvjestajtxt a: izvjestajRepositori.findAll()) {
		        	System.out.println("Introducing izvjestaj => " + a.getNaziv());
		        }*/
		        /*for (Fajl a: fajlservice.getAllItems()) {
		        	System.out.println("Introducing fajl => " + a.getNaziv() + a.getFajl());
		        }
		        for (Opisfajl a: opisfajlservice.getAllItems()) {
		        	System.out.println("Introducing opis fajl => " + a.getOpis());
		        }
		        */
		        
		        
		        
		        /*System.out.println(dataSource);
		        Metadata md = new Metadata(dataSource); 
		        md.IspisiMetapodatke(dataSource);*/
		        //Migration m = new Migration(dataSource);
		        //m.CreateDatabase();
		        //System.out.println("YESSSSSSSSSSS");
		        //Test treće baze: 
		        /*
		        for (Labtxt l: labService.getAllLabs()) {
	        	System.out.println("Postavka laba => " + l.GetNaziv());
	        	System.out.println("HEllo");
		        }
		        System.out.println("HEllo");
		        */
		        
		        
		        
				  	
		        //System.out.println(korisniciRepositori.addKorisnik("korisnikTest","korisniktest","test dodavanja","pass",false));
		    	

				
		        
	    }
	 
}
