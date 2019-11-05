package pu.employee;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import pu.employee.domain.HREmployee;

@Getter
@AllArgsConstructor( staticName = "of")
@Builder(toBuilder = true)
@EqualsAndHashCode
public class MyImmutableObject {
	
	private String value1;
	
	private String value2;
	
//	@Singular
//	private List<HREmployee> employees;
	

}
