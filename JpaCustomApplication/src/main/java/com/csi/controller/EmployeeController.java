package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")

    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")

    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")

    public ResponseEntity <Optional<Employee>> getDataById(@PathVariable long empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @PostMapping("/savebulkofdata")

    public ResponseEntity<List<Employee>> saveBulkOfData(@Valid @RequestBody List<Employee> employees) {
        return new ResponseEntity<>(employeeServiceImpl.saveBulkOfData(employees), HttpStatus.CREATED);

    }


    @GetMapping("/getdatabycontactnumber/{empContactNumber}")

    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")

    public ResponseEntity<Employee> getDataByEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmailId(empEmailId));
    }

    @GetMapping("/getdatabyempfirstname/{empFirstName}")

    public ResponseEntity<List<Employee>> getDataByEmpFirstName(@PathVariable String empFirstName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpFirstName(empFirstName));
    }

    @GetMapping("/getdatabyanyinput/{input}")

    public ResponseEntity<List<Employee>> getDataByAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByAnyInput(input));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>>sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpFirstName)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbyage")
    public ResponseEntity<List<Employee>>sortByAge(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpAge)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbycontactnumber")
    public ResponseEntity<List<Employee>> sortByContactNumber(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpContactNumber)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>>sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>>filterBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpSalary()>=50000).collect(Collectors.toList()));
    }
    @GetMapping("/filterbyAge/{empAge}")
    public ResponseEntity<List<Employee>>filterBySalary(@PathVariable int empAge){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpAge()>=18).collect(Collectors.toList()));
    }
    @GetMapping("/getalldata")

    public ResponseEntity <List<Employee>>getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<String> updateData(@PathVariable long empId, @Valid @RequestBody Employee employee) {
        Employee employee1= employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id does not Exist"));

        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpAge(employee.getEmpAge());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());

        employeeServiceImpl.updateData(employee1);

        return new ResponseEntity<>("Data Updated Successfully",HttpStatus.CREATED);

    }

    @DeleteMapping("/deletedatabyid/{empId}")

    public ResponseEntity<String> deleteDataById(@PathVariable long empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data deleted successfully");
    }

    @DeleteMapping("/deletealldata")

    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("Data Deleted successfully");
    }

}
