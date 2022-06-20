package com.chrisbees.management.services;


import com.chrisbees.management.model.Drivers;
import com.chrisbees.management.model.Students;
//import com.chrisbees.management.model.User;
import com.chrisbees.management.exceptions.ProjectIdException;
import com.chrisbees.management.model.Teacher;
import com.chrisbees.management.repositories.DriverRepository;
import com.chrisbees.management.repositories.StaffRepository;
import com.chrisbees.management.repositories.StudentRepostiory;
//import com.chrisbees.management.repositories.UserRepository;
import com.chrisbees.management.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepostiory studentRepostiory;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private TeachersRepository teachersRepository;

    @Autowired
    private DriverRepository driverRepository;
      public Students createStudent(Students student) {
          List<Students> studentsList= (List<Students>) studentRepostiory.findAll();
          for(Students s: studentsList){
              if(s.getFirstName().equals(student.getFirstName()) && s.getLastName().equals(student.getLastName())){
                  throw new ProjectIdException(student.getFirstName() + " " + student.getLastName() + " already exists");
              }
          }
          Teacher teacher2 = teachersRepository.findTeachersByGrade(student.getClassName());
          Drivers drivers = driverRepository.findDriversByBusName(student.getSchoolBus());
          if (teacher2 == null) {
              throw new ProjectIdException("Staff does not exists");
          }else if(drivers == null){
              throw new ProjectIdException(student.getSchoolBus() + " has no driver, please assign a driver to this Bus");
          }
          student.setTeacher(teacher2);
          student.setDrivers(drivers);
          student.setTeacherName(teacher2.getLastName() + " " + teacher2.getFirstName());
          //increment admission number by 1 for next student;

          int size = studentsList.size();
          student.setAdmissionNumber(size + 1000);
          //return image name
          return studentRepostiory.save(student);


    }

    public Students findStudentById(Long id) {
        Optional<Students> student = studentRepostiory.findById(id);

        if (!student.isPresent()) {
            throw new ProjectIdException("Student does not exists");
        }

        return student.get();
    }

    public Iterable<Students> findAllStudents() {
        return studentRepostiory.findAll();
    }

    public void deleteStudentById(Long id) {
        Students students = findStudentById(id);
        Teacher teacher = students.getTeacher();
        teacher.getStudents().removeIf(student -> student.getId().equals(id));
        studentRepostiory.delete(students);

    }

    public Students updateProjectById(Long id, Students newStudent) {
        Optional<Students> student = studentRepostiory.findById(id);
        if (!student.isPresent()) {
            throw new ProjectIdException("Project ID " + id + " does not exists");
        }
        student.get().setFirstName(newStudent.getFirstName());
        student.get().setLastName(newStudent.getLastName());
        student.get().setMiddleName(newStudent.getMiddleName());
        student.get().setClassName(newStudent.getClassName());
        student.get().setHomeAddress(newStudent.getHomeAddress());
        student.get().setNationality(newStudent.getNationality());
        student.get().setParentsEmail(newStudent.getParentsEmail());
        student.get().setParentsName(newStudent.getParentsName());
        student.get().setParentsPhoneNumber(newStudent.getParentsPhoneNumber());
        student.get().setParentsOccupation(newStudent.getParentsOccupation());
        student.get().setStateOfOrigin(newStudent.getStateOfOrigin());
        student.get().setGender(newStudent.getGender());

        return studentRepostiory.save(student.get());
    }

    public void uploadProfilePicture(MultipartFile file, Long id) {
          //accept image file
          //save image file
          //save image name to database
          //return image name
        try {
            Students students = findStudentById(id);
            System.out.println(students);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            String filePath = "/src/main/resources/static/images/" + fileName;
//            File destination = new File(filePath);
//            file.transferTo(destination);
            if (fileName.contains("..")) {
                throw new ProjectIdException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            students.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            studentRepostiory.save(students);
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("close");
        }
    }
}
