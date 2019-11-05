package pu.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName="of")
@Builder(toBuilder=true)
@ToString
@EqualsAndHashCode
public class HREmployeeEvent {
	
	private HREmployee employee;
	private String message;


}
