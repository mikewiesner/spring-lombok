package pu.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Test;

import pu.employee.domain.HREmployee;

public class HashTest {
	
	
	@Test
	public void testEquals() throws Exception {
		MyObject myObject1 = new MyObject("OldValue1", "OldValue2");
		MyObject myObject2 = new MyObject("OldValue1", "OldValue2");
		MyObject myObject3 = new MyObject("NewValue1", "OldValue2");
		
		assertThat(myObject1).isEqualTo(myObject2);
		
		assertThat(myObject1).isNotEqualTo(myObject3);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testWrongHashCode() throws Exception {
		HashSet<MyObject> hashSet = new HashSet<>();
		
		MyObject myObject = new MyObject("OldValue1", "OldValue2");
		
		
		hashSet.add(myObject);

		assertThat(hashSet).contains(myObject);
	
		myObject.setValue1("NewValue1");

		assertThat(hashSet.iterator().next()).isEqualTo(myObject);

		assertThat(hashSet).contains(myObject);
		assertThat(hashSet.contains(myObject)).isTrue();
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testCorrectHashCode() throws Exception {
		HashSet<MyImmutableObject> hashSet = new HashSet<>();
		
		MyImmutableObject myObject = MyImmutableObject.builder()
				.value1("OldValue1")
				.value2("OldValue2").build();
		
		hashSet.add(myObject);
	
		assertThat(hashSet).contains(myObject);
		
		
		hashSet.remove(myObject);
		MyImmutableObject newObject = myObject.toBuilder().value1("NewValue1").build();
		hashSet.add(newObject);

		assertThat(hashSet.iterator().next()).isEqualTo(newObject);

		assertThat(hashSet).contains(newObject);
		assertThat(hashSet.contains(newObject)).isTrue();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test
//	public void testList() throws Exception {
//		HREmployee mike = new HREmployee("Mike", "info@mwiesner.com", "Mike", "Wiesner", LocalDate.now(), LocalDate.now());
//		
//		LinkedList<HREmployee> list = new LinkedList<>();
//		list.add(mike);
//		
//		MyImmutableObject myObject = MyImmutableObject.builder()
//				.value1("OldValue1")
//				.value2("OldValue2")
//				.employees(list).build();
//		
//		assertThat(myObject.getEmployees()).contains(mike);
//
//	}
	
	//Stream.of(mike).collect(Collectors.toList())

}
