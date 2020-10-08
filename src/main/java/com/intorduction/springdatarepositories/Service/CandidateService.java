package com.intorduction.springdatarepositories.Service;

import com.intorduction.springdatarepositories.Repository.CandidateJpaRepository;
import com.intorduction.springdatarepositories.Repository.CandidateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class CandidateService implements CommandLineRunner {

    private CandidateJpaRepository candidateRepository;

    public CandidateService(CandidateJpaRepository candidateRepository)
    {
        this.candidateRepository=candidateRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /*Shows the # of candidate presents, entries in the candidate table*/
        System.out.println("Count "+this.candidateRepository.count());

        /*But what if we want count for only last name, go for derived query*/
        System.out.println("Count for email "+this.candidateRepository.countByEmail("nishu@gmail.com"));
        System.out.println("Count for last name "+this.candidateRepository.countBylastName("Tayade"));
        this.candidateRepository.deleteByLastName("Tayade");


    }
}
