package com.intorduction.springdatarepositories.Service;

import com.intorduction.springdatarepositories.Repository.CandidateJpaRepository;
import com.intorduction.springdatarepositories.Repository.MyRepository;
import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class CandidateService implements CommandLineRunner {

    private CandidateJpaRepository candidateRepository;
    private MyRepository myRepository;


    public CandidateService(CandidateJpaRepository candidateRepository,MyRepository myRepository)
    {
        this.candidateRepository=candidateRepository;
        this.myRepository=myRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /*Shows the # of candidate presents, entries in the candidate table*/
        System.out.println("Count "+this.candidateRepository.count());
        /*But what if we want count for only last name, go for derived query*/
        System.out.println("Count for email "+this.candidateRepository.countByEmail("nishu@gmail.com"));
        System.out.println("Count for last name "+this.candidateRepository.countByLastName("Tayade"));

        /*to show case @NoRepositoryBeanDefination*/
        this.myRepository.findAll().stream().forEach(System.out::println);


        /*Paging by returning list*/
        Pageable pageable= PageRequest.of(0,2);
        System.out.println("Is result empty "+
                this.candidateRepository.findByFirstName("Nishikant",null).isEmpty());
        this.candidateRepository.findByFirstName("Nishikant", pageable).stream().forEach(System.out::println);

        /*Paging by returning Page*/
        Page<Candidate> lastNameCriteriaCandidates =
                this.candidateRepository.findByLastName("Tayade", PageRequest.of(0, 5));
        System.out.println("Total Elements "+lastNameCriteriaCandidates.getTotalElements());
        System.out.println("Total Pages" +lastNameCriteriaCandidates.getTotalPages());

        /*Paging by retuning slice*/
        Slice<Candidate> emailCriteriaCandidate =
                this.candidateRepository.findByEmail("nishu@gmail.com", PageRequest.of(0, 5));
        System.out.println(emailCriteriaCandidate.getNumberOfElements() +" "+emailCriteriaCandidate.hasNext() +" "+
                emailCriteriaCandidate.hasPrevious());

        /*Limiting the result with Top and First*/
        System.out.println(this.candidateRepository.findTop3ByFirstNameIsContaining("n").
                stream().map(Candidate::getFirstName).collect(Collectors.toList()));

        

    }
}
