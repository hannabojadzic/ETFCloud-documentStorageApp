package com.tim11.demo.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.Izvjestajtxt;


@Repository
//mongo repositori za tekstualne materijale za izvjestaje
public interface izvjestajtxtRepositori extends MongoRepository<Izvjestajtxt, Integer> {

}
