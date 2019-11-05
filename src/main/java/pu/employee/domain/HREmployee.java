package pu.employee.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder(toBuilder=true)
@ToString
@EqualsAndHashCode
@Entity
@Immutable
public class HREmployee {
	
	
	private HREmployee() {
	}

	
	@Id
	private String id;
	private String email;
	private String firstname;
	private String lastname;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;

}
