package com.chrisbees.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String driverName;
    private String busName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Staff staff;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "drivers", targetEntity = Students.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Students> students = new ArrayList<>();

    @Override
    public String toString() {
        return "Driver";
    }
}
