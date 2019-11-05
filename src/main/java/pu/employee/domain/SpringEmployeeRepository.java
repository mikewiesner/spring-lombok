package pu.employee.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpringEmployeeRepository extends CrudRepository<HREmployee, String>, EmployeeRepository  {

	List<HREmployee> findByEmail(String email);
}
