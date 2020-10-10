package com.intorduction.springdatarepositories.Resource;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private String email;

    public Candidate findByFirstNameAndLastName(String firstName,String lastName)
    {
        System.out.println("This method is called from named query execution");
        return new Candidate();
    }
}
