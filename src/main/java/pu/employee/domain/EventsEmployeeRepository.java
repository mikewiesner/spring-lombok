package pu.employee.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor(onConstructor_={@Autowired}) 
public class EventsEmployeeRepository implements EmployeeRepository {
	
	private final ApplicationEventPublisher publisher;

	@Override
	public HREmployee save(HREmployee employee) {
		this.publisher.publishEvent(HREmployeeEvent.of(employee, "created"));
		return null;
	}
	
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

}
