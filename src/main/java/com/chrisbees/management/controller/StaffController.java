package com.chrisbees.management.controller;

import com.chrisbees.management.model.Staff;
import com.chrisbees.management.services.MapValidationErrorService;
import com.chrisbees.management.services.StaffService;
//import com.chrisbees.management.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class StaffController {


    @Autowired
    private StaffService staffService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping(value = "/addStaff")
    public ResponseEntity<?> addStaff(@Valid @RequestBody Staff staff,
                                      BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;
        Staff staff1 = staffService.createStaff(staff);
        return new ResponseEntity<Staff>(staff1, HttpStatus.CREATED);
    }

    @GetMapping("/allStaff")
    public ResponseEntity<?> getAllStaff() {
        Iterable<Staff> staff = staffService.allStaff();
        return new ResponseEntity<Iterable<Staff>>(staff, HttpStatus.OK);
    }

    @PutMapping("/updateStaff/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody Staff staff, @PathVariable Long id) {
        staffService.updateStaffById(id, staff);
        return new ResponseEntity<String>("Staff with ID " + id + " successfully Updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaffById(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return new ResponseEntity<String>("Staff with ID " + id + " was deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.getStaff(id);
    }

    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfilePicture(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        staffService.uploadProfilePicture(file, id);
        return new ResponseEntity<String>("Profile Picture Uploaded", HttpStatus.OK);
    }
}
