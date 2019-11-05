package pu.employee.domain;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventLogger {
	
	
	@EventListener
	public void handleHREmployeeEvent(HREmployeeEvent event) {
		System.out.println(event.toString());
	}

}
