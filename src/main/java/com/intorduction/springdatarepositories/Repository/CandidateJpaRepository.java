package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*This is specific Repository*/
public interface CandidateJpaRepository extends JpaRepository<Candidate,Long> {

    /*Derived queries*/
    long countByEmail(String email);
    long countByLastName(String lastName);
    void deleteByLastName(String lastName);

    /*Here we are not specifying any pageable in method name, as it is a special parameter
    * type recognized by infrastructure and applies that operation of special parameter to
    * result of the query*/
    List<Candidate> findByFirstName(String firstName, Pageable pageable);
    Page<Candidate> findByLastName(String lastName,Pageable pageable);
    Slice<Candidate> findByEmail(String email, Pageable  pageable);

    /*limiting the result*/
    List<Candidate> findTop3ByFirstNameIsContaining(String name);
}

