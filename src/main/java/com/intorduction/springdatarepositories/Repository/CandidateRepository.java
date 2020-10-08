package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
/*Generic Repository- not tied to any specific persistence technology*/
public interface CandidateRepository extends CrudRepository<Candidate,Long> {
}
