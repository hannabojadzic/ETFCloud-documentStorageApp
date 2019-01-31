package com.tim11.demo.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tim11.demo.Entities.Labtxt;
import com.tim11.demo.Entities.Predavanjetxt;

@Repository
public interface LabtxtRepositori extends MongoRepository<Labtxt, Integer> {

}