package pu.employee.domain;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_={@Autowired}) 
@Command
public class EmployeeCommandImpl implements EmployeeCommandOutPort {

	@NonNull
	private EmployeeRepository employeeRepository;
	
	private final ApplicationEventPublisher publisher;
	
	
	
	@PostConstruct
	public void dataInitializer() {
		employeeRepository.deleteAll();
		HREmployee max = new HREmployee("max", "no-reply@example.com", "Max", "Mustermann",LocalDate.now(), LocalDate.of(2000, 01, 01));
		employeeRepository.save(max);
		HREmployee mike = new HREmployee("mike", "mike@example.com", "Mike", "Wiesner", LocalDate.of(2007, 04, 01), LocalDate.of(2015,10,21));
		employeeRepository.save(mike);
		HREmployee christian = new HREmployee("christian", "christian@example.com", "Christian", "Harms", LocalDate.of(2006, 04, 01), LocalDate.of(2015,12,24));
		employeeRepository.save(christian);
	}
	
	
	public HREmployee newEmployee(HREmployee employee) {
		HREmployee newEmployee = employee.toBuilder()//
			.id(employee.getFirstname().toLowerCase())//TODO: We really need to fix that!
			.build();
		HREmployee savedEmployee = employeeRepository.save(newEmployee);

		
		return savedEmployee;
	}


	
	
	
	// Code snippets
	
//	this.publisher.publishEvent(HREmployeeEvent.of(newEmployee, "created"));
//	this.employeeChannel.send(new GenericMessage<HREmployeeEvent>(HREmployeeEvent.of(newEmployee, "created")));
//	this.messagingTemplate.convertAndSend(HREmployeeEvent.of(newEmployee, "created"));
	
//	this.employeeEventPublisher.publishEmployeeCreateEvent(HREmployeeEvent.of(newEmployee, "created"));
	
	
	
	
	
//	private final ApplicationEventPublisher publisher;

//	@Qualifier("employeeChannel")
//	private final MessageChannel employeeChannel;
	
//	private final MessagingTemplate messagingTemplate;
		
//	private final EmployeeEventPublisher employeeEventPublisher;
	
	
	
//	@NonNull
//	private List<EmployeeRepository> employeeRepository;
	
//	HREmployee savedEmployee = employeeRepository.stream().map(e -> e.save(newEmployee)).filter(Objects::nonNull).findFirst().get();

	
//	@PostConstruct
//	public void dataInitializer() {
//		employeeRepository.forEach(e -> e.deleteAll());
//		HREmployee max = HREmployee.of("max", "no-reply@example.com", "Max", "Mustermann",LocalDate.now(), LocalDate.of(2000, 01, 01));
//		employeeRepository.forEach(e -> e.save(max));
//		HREmployee mike = HREmployee.of("mike", "mike@example.com", "Mike", "Wiesner", LocalDate.of(2007, 04, 01), LocalDate.of(2015,10,21));
//		employeeRepository.forEach(e -> e.save(mike));
//		HREmployee christian = HREmployee.of("christian", "christian@example.com", "Christian", "Harms", LocalDate.of(2006, 04, 01), LocalDate.of(2015,12,24));
//		employeeRepository.forEach(e -> e.save(christian));
//	}
	
	
}
