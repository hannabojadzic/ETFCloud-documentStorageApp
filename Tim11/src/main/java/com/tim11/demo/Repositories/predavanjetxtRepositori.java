package com.tim11.demo.Repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tim11.demo.Entities.Predavanjetxt;



@Repository
//mongo repositori za tekstualne materijale za predavanja
public interface predavanjetxtRepositori extends MongoRepository<Predavanjetxt, Integer> {

}
