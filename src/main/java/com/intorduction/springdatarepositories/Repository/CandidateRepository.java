package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.repository.CrudRepository;


/*Generic Repository- not tied to any specific persistence technology*/
public interface CandidateRepository extends CrudRepository<Candidate,Long> {
}
