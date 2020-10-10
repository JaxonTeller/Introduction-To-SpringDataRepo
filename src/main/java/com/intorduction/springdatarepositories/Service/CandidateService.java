package com.intorduction.springdatarepositories.Service;

import com.intorduction.springdatarepositories.Repository.CandidateJpaRepository;
import com.intorduction.springdatarepositories.Repository.MyRepository;
import com.intorduction.springdatarepositories.Resource.Candidate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.util.Streamable;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class CandidateService implements CommandLineRunner {

    private final CandidateJpaRepository candidateRepository;
    private  final MyRepository myRepository;

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
        /*Will throw exception as we are using @NonNullApi on package level for CandidateJpaRepository,
        * to override it provide @Nullable for param pageable in repository*/
        Pageable pageable= PageRequest.of(0,2);
        System.out.println("Is result empty "+
                this.candidateRepository.findByFirstName("Nishikant", null).isEmpty());
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

        /*Limiting the result with Top and First and Using Streamable as return type*/
        System.out.println(this.candidateRepository.findTop3ByFirstNameIsContaining("n").
                stream().map(Candidate::getFirstName).collect(Collectors.toList()));

        /*@Query*/
        System.out.println(this.candidateRepository.findByFirstName("Nishikant"));

        /*@Query native query*/
        System.out.println(this.candidateRepository.findByFirstNameNativeQuery("Nant"));

        /*@Query with param binding*/
        System.out.println(this.candidateRepository.findByFirstNameWithParam("Nant"));

        this.candidateRepository.updateTheFirstName("nishukant@gmail.com","Nishikant","Tayade");
    }


}
