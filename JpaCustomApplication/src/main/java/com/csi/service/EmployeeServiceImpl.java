package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeDaoImpl employeeDaoImpl;
    public Employee signUp(Employee employee) {
       return employeeDaoImpl.signUp(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        return employeeDaoImpl.signIn(empEmailId,empPassword);
    }

    @Cacheable(value = "empId")
    public Optional<Employee> getDataById(long empId) {
        return employeeDaoImpl.getDataById(empId);
    }

    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return  employeeDaoImpl.saveBulkOfData(employees);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeDaoImpl.getDataByContactNumber(empContactNumber);
    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeDaoImpl.getDataByEmailId(empEmailId);
    }

    public List<Employee> getDataByEmpFirstName(String empFirstName) {
        return employeeDaoImpl.getDataByEmpFirstName( empFirstName);
    }

    public List<Employee> getDataByAnyInput(String input) {
        return employeeDaoImpl.getDataByAnyInput( input);
    }

    public List<Employee> getAllData() {
        return employeeDaoImpl.getAllData();
    }

    public Employee updateData(Employee employee) {
       return   employeeDaoImpl.updateData(employee);
    }

    public void deleteDataById(long empId) {
         employeeDaoImpl.deleteDataById(empId);
    }

    public void deleteAllData() {
        employeeDaoImpl.deleteAllData();
    }
}
