package com.luv2code.springbootcrmdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springbootcrmdemo.entity.Employee;

//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
