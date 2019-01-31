package com.tim11.demo.Repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tim11.demo.Entities.Korisnici;

@Repository
//repositori Korisnici Oracle
public interface KorisniciRepositori extends CrudRepository<Korisnici, Integer> {
	@Query(value="INSERT INTO Korisnici (ime,prezime,username,password,admin,enabled,role) VALUES(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	public String addKorisnik(String Ime,String Prezime,String Username,String Password,Boolean Admin,Boolean enabled,String role); 
	
}

