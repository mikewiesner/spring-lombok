package pu.employee;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import pu.employee.config.web.SimpleSpringWebMvcConfig;
import pu.employee.domain.EmployeeCommandOutPort;
import pu.employee.domain.EmployeeQueryInPort;
import pu.employee.domain.EventLogger;
import pu.employee.domain.HREmployee;
import pu.employee.domain.HREmployeeEvent;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SimpleSpringWebMvcConfig.class)
public class EmployeeCommandTest {
	
	@Autowired
	private EmployeeCommandOutPort command;
	
	@Autowired
	private EmployeeQueryInPort query;
	
	@MockBean
	private EventLogger eventLogger;
	

	
	@Test
	public void testCreateEmployee() throws Exception {
	
		HREmployee mike = new HREmployee("test1", "test1@example.com", "Test1", "Test2", LocalDate.now(), LocalDate.now());
		HREmployee savedEntity = command.newEmployee(mike);
		
		//Mockito.verify(eventLogger).handleHREmployeeEvent(HREmployeeEvent.of(mike, "created"));
		
		HREmployee loadedEntitiy = query.getEmployeeByEmail("test1@example.com");
		assertThat(loadedEntitiy).isEqualTo(savedEntity);
		
		
	}

}
