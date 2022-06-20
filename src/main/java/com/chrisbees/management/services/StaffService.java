package com.chrisbees.management.services;

import com.chrisbees.management.exceptions.ProjectIdException;
import com.chrisbees.management.exceptions.ProjectNotFoundException;
import com.chrisbees.management.model.*;
import com.chrisbees.management.repositories.StaffRepository;
import com.chrisbees.management.repositories.StudentRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StudentRepostiory studentRepostiory;


    public Staff createStaff(Staff staff) {

        Teacher teachers = new Teacher();
        Cleaners cleaners = new Cleaners();
        Drivers drivers = new Drivers();
        if (staff.getId() != null) {
            throw new ProjectIdException("Staff " + teachers.getId() + " already exists");
        }
        if (staff.getPosition().equals(teachers.toString().trim())) {
            List<Teacher> teachersList = new ArrayList<>();
            teachersList.add(teachers);
            staff.setTeachers(teachersList);
            teachers.setStaff(staff);
            teachers.setGrade(staff.getGrade());
            teachers.setFirstName(staff.getFirstName());
            teachers.setLastName(staff.getLastName());
            staff.setSchoolBus(null);
        }else if(staff.getPosition().equals(cleaners.toString().trim())){
            List<Cleaners> cleanersList = new ArrayList<>();
            cleanersList.add(cleaners);
            staff.setCleaners(cleanersList);
            cleaners.setStaff(staff);
            staff.setGrade(null);
            staff.setSchoolBus(null);
        } else if(staff.getPosition().equals(drivers.toString().trim())){
            List<Drivers> driversList = new ArrayList<>();
            driversList.add(drivers);
            staff.setDrivers(driversList);
            drivers.setBusName(staff.getSchoolBus());
            drivers.setDriverName(staff.getLastName());
            drivers.setStaff(staff);
            staff.setGrade(null);
        }else if(!staff.getPosition().equals(teachers.toString().trim()) || staff.getPosition().equals(cleaners.toString().trim())){
            throw new ProjectIdException("Please enter staff position");
        }
        return staffRepository.save(staff);
    }

    public Iterable<Staff> allStaff() {
        return staffRepository.findAll();
    }

    public Staff updateStaffById(Long id, Staff newStaff) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (!staff.isPresent()) {

            throw new ProjectNotFoundException("Staff with id: " + id + " not found", id);
        }
        staff.get().setFirstName(newStaff.getFirstName());
        staff.get().setLastName(newStaff.getLastName());
        staff.get().setMiddleName(newStaff.getMiddleName());
        staff.get().setGuarantorsName(newStaff.getGuarantorsName());
        staff.get().setGuarantorsPhone(newStaff.getGuarantorsPhone());
        staff.get().setHomeAddress(newStaff.getHomeAddress());
        staff.get().setPhoneNumber(newStaff.getPhoneNumber());
        staff.get().setBirthDate(newStaff.getBirthDate());
        staff.get().setGender(newStaff.getGender());
        staff.get().setNationality(newStaff.getNationality());
        staff.get().setPosition(newStaff.getPosition());
        staff.get().setSalary(newStaff.getSalary());
        staff.get().setSchoolBus(newStaff.getSchoolBus());

        return staffRepository.save(staff.get());
    }

    public Staff getStaff(Long id) {
        return staffRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(
                "Staff with id: " + id + " not found", id));
    }

    public void deleteStaff(Long id) {
        Staff staff = getStaff(id);
        staffRepository.delete(staff);
    }

    public void uploadProfilePicture(MultipartFile file, Long id) {
        //accept image file
        //save image file
        //save image name to database
        //return image name
        try {
            Staff staff = getStaff(id);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            String filePath = "/src/main/resources/static/images/" + fileName;
//            File destination = new File(filePath);
//            file.transferTo(destination);
            if (fileName.contains("..")) {
                throw new ProjectIdException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            staff.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            staffRepository.save(staff);
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("upload failed");
        }
    }
}
