package com.chrisbees.management.controller;

import com.chrisbees.management.model.Students;
import com.chrisbees.management.services.MapValidationErrorService;
import com.chrisbees.management.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> createNewStudent(@Valid @RequestBody Students student, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;
        Students students = studentService.createStudent(student);
        return new ResponseEntity<Students>(students, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        Students project = studentService.findStudentById(id);
        return new ResponseEntity<Students>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Students> getAllProjects() {
        return studentService.findAllStudents();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentProfileById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<String>("Student with ID " + id + " was deleted", HttpStatus.OK);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody Students student, @PathVariable Long id) {
        studentService.updateProjectById(id, student);
        return new ResponseEntity<String>("Student with ID " + id + " successfully Updated", HttpStatus.OK);
    }

    //create a multi-part request
    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfilePicture(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        studentService.uploadProfilePicture(file, id);
        return new ResponseEntity<String>("Profile Picture Uploaded", HttpStatus.OK);
    }
}
