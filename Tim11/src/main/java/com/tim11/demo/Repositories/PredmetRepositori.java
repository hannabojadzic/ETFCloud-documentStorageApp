package com.tim11.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.tim11.demo.Entities.Predmet;


@Repository
public interface PredmetRepositori extends CrudRepository<Predmet, Integer> {

}