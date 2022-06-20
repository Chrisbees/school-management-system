package com.chrisbees.management.repositories;

import com.chrisbees.management.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends CrudRepository<Teacher, Long> {
        public Teacher findTeachersByGrade(String grade);

}
