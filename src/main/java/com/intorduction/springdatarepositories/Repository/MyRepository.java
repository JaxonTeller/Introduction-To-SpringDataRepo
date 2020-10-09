package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/*As this repo is user defined the implementation is provided by SimpleJpaRepo class*/
public interface MyRepository extends JpaRepository<Candidate,Long> {

    List<Candidate> findAll();
}
