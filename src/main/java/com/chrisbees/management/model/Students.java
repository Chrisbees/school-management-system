package com.chrisbees.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Students {


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
    private Date birthDate;
    @NotBlank(message = "Nationality cannot be blank")
    @Size(max = 100, message = "Nationality cannot be more than 100 characters")
    private String nationality;
    @NotBlank(message = "State of origin cannot be blank")
    @Size(max = 100, message = "State of origin cannot be more than 100 characters")
    private String stateOfOrigin;
    @NotBlank(message = "Home address cannot be blank")
    @Size(max = 100, message = "Home address cannot be more than 100 characters")
    private String homeAddress;
    @NotBlank(message = "Phone number cannot be blank")
    @Size(max = 100, message = "Phone number cannot be more than 100 characters")
    private String parentsPhoneNumber;
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 100, message = "Email cannot be more than 100 characters")
    private String parentsEmail;
    @Size(max = 100, message = "Class cannot be more than 100 characters")
    private String className;
    @Column(updatable = false)
    private int admissionNumber = 1000;
    @NotBlank(message = "Guardian's name cannot be blank")
    @Size(max = 100, message = "Guardian's name cannot be more than 100 characters")
    private String parentsName;
    @NotBlank(message = "Guardian's occupation cannot be blank")
    @Size(max = 100, message = "Guardian's occupation cannot be more than 100 characters")
    private String parentsOccupation;
    @NotBlank(message = "Gender cannot be blank")
    private String gender;
    private String schoolBus;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    private String teacherName;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "drivers_id")
    @JsonIgnore
    private Drivers drivers;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;

//    @Transient
//    public String getPhotosImagePath() {
//        if(image.isEmpty() && id == 0){
//            return "upload failed";
//        }else {
//            return "/studentImages/" + image;
//        }
//    }


//    @ManyToOne
//    @JsonIgnore
//    private User user;
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
}
