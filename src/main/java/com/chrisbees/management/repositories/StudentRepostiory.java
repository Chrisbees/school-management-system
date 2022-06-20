package com.chrisbees.management.repositories;

import com.chrisbees.management.model.Students;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface StudentRepostiory extends CrudRepository<Students, Long> {
    public Optional<Students> findStudentsByAdmissionNumber(Integer number);
}
