package pu.employee.domain;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface EmployeeEventPublisher {
	
	
	@Gateway(requestChannel = "employeeChannel")
	public void publishEmployeeCreateEvent(HREmployeeEvent employeeEvent);

}
