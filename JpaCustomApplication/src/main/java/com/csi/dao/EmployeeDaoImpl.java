package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee) {
        return  employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag =false;
        Employee employee=employeeRepositoryImpl.findByEmpEmailIdAndEmpPassword(empEmailId,empPassword);

        if(employee !=null){
            flag=true;

        }return flag;
    }

    public Optional<Employee> getDataById(long empId) {
        return employeeRepositoryImpl.findById(empId);
    }

    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return employeeRepositoryImpl.saveAll(employees);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeRepositoryImpl.findByEmpContactNumber(empContactNumber);
    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeRepositoryImpl.findByEmpEmailId(empEmailId);
    }


    public List<Employee> getDataByEmpFirstName(String empFirstName) {
        return employeeRepositoryImpl.findByEmpFirstName(empFirstName);
    }

    public List<Employee> getDataByAnyInput(String input) {

        List<Employee> employeeList=new ArrayList<>();

        for (Employee employee: getAllData()){

            if(employee.getEmpFirstName().equals(input)
           || employee.getEmpLastName().equals(input)
            ||employee.getEmpAddress().equals(input)
            || String.valueOf(employee.getEmpId()).equals(input)
            ||String.valueOf(employee.getEmpContactNumber()).equals(input)
            || String.valueOf(employee.getEmpAge()).equals(input)
            ||employee.getEmpEmailId().equals(input)){

                employeeList.add(employee);
            }


        }
        return employeeList;
    }

    public List<Employee> getAllData() {
        return employeeRepositoryImpl.findAll();
    }

    public Employee updateData(Employee employee) {
       return employeeRepositoryImpl.save(employee);

    }

    public void deleteDataById(long empId) {
        employeeRepositoryImpl.deleteById(empId);
    }

    public void deleteAllData() {
        employeeRepositoryImpl.deleteAll();
    }
}
