package com.challenge.java.agenda.repository;

import com.challenge.java.agenda.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("from Person p where p.name =:name")
    List<Person> findByName(@Param("name") String name);
}
