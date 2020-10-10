package com.intorduction.springdatarepositories.Repository;

import com.intorduction.springdatarepositories.Resource.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

/*This is specific Repository*/
public interface CandidateJpaRepository extends JpaRepository<Candidate,Long>{

    /*Derived queries*/
    long countByEmail(String email);
    long countByLastName(String lastName);
    void deleteByLastName(String lastName);

    /*Here we are not specifying any pageable in method name, as it is a special parameter
    * type recognized by infrastructure and applies that operation of special parameter to
    * result of the query*/
    List<Candidate> findByFirstName(String firstName, @Nullable Pageable pageable);
    Page<Candidate> findByLastName(String lastName,Pageable pageable);
    Slice<Candidate> findByEmail(String email, Pageable  pageable);

    /*limiting the result*/
    Streamable<Candidate> findTop3ByFirstNameIsContaining(String name);

    /*@Query*/
    @Query(value = "select c.lastName from Candidate c where c.firstName=?1")
    List<String> findByFirstName(String firstName);

    @Query(value = "select * from Candidate where first_name=?1",nativeQuery = true)
    Candidate findByFirstNameNativeQuery(String firstName);

    @Query(value = "select c from Candidate c where c.firstName=:firstName")
    Candidate findByFirstNameWithParam(@Param("firstName") String firstName);

    @Modifying
    @Query(value = "update Candidate c set c.email=:email where c.firstName=:firstName and c.lastName=:lastName")
    int updateTheFirstName(@Param("email")String email,@Param("firstName") String firstName,@Param("lastName") String lastName);


}

