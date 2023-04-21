package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue

    private long empId;

    private String empFirstName;

    private String empLastName;

    private String empAddress;

    private double empSalary;

    @Column(unique = true)
    @Range(min = 1000000000,max = 9999999999l,message = "Contact number should be contain 10 digits")
    private long empContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Kolkata")
    private Date empDOB;

    private int empAge;

    @Column(unique = true)
    @Email(message = "Email must be Valid")
    private String empEmailId;

    @Size(min = 4,message = "Password must be contain 4 chracters")
    private String empPassword;

}
