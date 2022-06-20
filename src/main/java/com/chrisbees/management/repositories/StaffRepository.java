package com.chrisbees.management.repositories;

import com.chrisbees.management.model.Staff;
import com.chrisbees.management.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

    public Staff findStaffByGrade(String grade);
    public Staff findStaffBySchoolBus(String busName);
}
