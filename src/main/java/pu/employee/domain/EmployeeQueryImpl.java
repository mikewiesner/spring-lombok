package pu.employee.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_={@Autowired}) 
@Query
public class EmployeeQueryImpl implements EmployeeQueryInPort {
	
	@NonNull
	private SpringEmployeeRepository employeeRepository;
	
	public HREmployee getEmployeeByEmail(String email) {
		List<HREmployee> employeeList = employeeRepository.findByEmail(email);
		if (employeeList.size() != 1) {
			throw new IllegalStateException("Expected one matching employee, but found "+employeeList.size()+" with the email adress "+email);
		}
		return employeeList.get(0);
	}
	
	@Secured("ROLE_HR")
	public List<HREmployee> getAllEmployees() {
		Iterable<HREmployee> findAll = employeeRepository.findAll();
		ArrayList<HREmployee> arrayList = new ArrayList<HREmployee>();
		findAll.forEach(arrayList::add);
		return arrayList;
	}
	

}
