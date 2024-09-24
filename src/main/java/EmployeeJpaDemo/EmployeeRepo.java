package EmployeeJpaDemo;

import org.springframework.data.jpa.repository.JpaRepository;

import EmployeeJpaDemo.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
