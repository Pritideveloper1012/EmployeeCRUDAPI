package com.self.in.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.in.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
