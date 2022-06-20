package com.chrisbees.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 100, message = "First name cannot be more than 100 characters")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name cannot be more than 100 characters")
    private String lastName;
    private String middleName;
    @JsonFormat(pattern = "yyyy-mm-dd")
//    @NotBlank(message = "Date of birth cannot be blank")
//    @Size(max = 100, message = "Date of birth cannot be more than 100 characters")
    private Date birthDate;
    @NotBlank(message = "Nationality cannot be blank")
    @Size(max = 100, message = "Nationality name cannot be more than 100 characters")
    private String nationality;
    @NotBlank(message = "State of Origin cannot be blank")
    @Size(max = 100, message = "State of Origin cannot be more than 100 characters")
    private String stateOfOrigin;
    @NotBlank(message = "Home Address cannot be blank")
    @Size(max = 150, message = "Home Address cannot be more than 100 characters")
    private String homeAddress;
    @NotBlank(message = "Phone Number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Position cannot be blank")
    @Size(max = 100, message = "Positon cannot be more than 100 characters")
    private String position;
    private String grade;
    private String salary;
    private String schoolBus;
    @NotBlank(message = "Guarantor's Name cannot be blank")
    @Size(max = 100, message = "Guarantor's Name cannot be more than 100 characters")
    private String guarantorsName;
    @NotBlank(message = "Guarantor's Phone Number cannot be blank")
    @Size(max = 100, message = "Guarantor's Phone Number cannot be more than 100 characters")
    private String guarantorsPhone;
    private String gender;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;
    private Date created_At;
    private Date updated_At;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Teacher.class, fetch = FetchType.EAGER, mappedBy = "staff", orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, targetEntity = Drivers.class, fetch = FetchType.LAZY, mappedBy = "staff", orphanRemoval = true)
    private List<Drivers> drivers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Cleaners.class, fetch = FetchType.LAZY, mappedBy = "staff")
    private List<Cleaners> cleaners = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }


//
//    //OneToOne with project
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "project_id", nullable = false)
//    @JsonIgnore
//    private Project project;
//    //OneToMany projectTasks
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
//    private List<ProjectTask> projectTasks = new ArrayList<>();

}
