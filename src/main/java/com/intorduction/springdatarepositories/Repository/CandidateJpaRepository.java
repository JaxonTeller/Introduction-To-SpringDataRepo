package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

/*This is specific Repository*/
public interface CandidateJpaRepository extends JpaRepository<Candidate,Long> {

    /*Derived queries*/
    long countByEmail(String email);
    long countBylastName(String lastName);
    void deleteByLastName(String lastName);
}

