package pu.employee.domain;

public interface EmployeeRepository {
	
	HREmployee save(HREmployee employee);
	
	void deleteAll();

}
