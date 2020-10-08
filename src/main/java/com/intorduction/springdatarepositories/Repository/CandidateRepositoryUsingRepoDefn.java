package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.RepositoryDefinition;

@NoRepositoryBean
@RepositoryDefinition(domainClass = Candidate.class,idClass = Long.class)
public interface CandidateRepositoryUsingRepoDefn {
}
