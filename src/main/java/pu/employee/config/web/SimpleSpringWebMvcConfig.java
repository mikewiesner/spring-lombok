package pu.employee.config.web;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.messaging.MessageChannel;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pu.employee.domain.EventLogger;
import pu.employee.domain.HREmployeeEvent;

@EnableWebMvc
@SpringBootApplication(scanBasePackages={"pu.employee.web","pu.employee.domain"})
@EnableJpaRepositories(basePackages= "pu.employee.domain")
@Import(SecurityConfig.class)
@IntegrationComponentScan("pu.employee.domain")
public class SimpleSpringWebMvcConfig implements WebMvcConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringWebMvcConfig.class, args);
	}
	
	@Bean
	public DataSource dataSource() {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("pu.employee.domain");
		factory.setDataSource(dataSource());
		return factory;
	}
	
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public FilterRegistrationBean hiddenHttpMethodFilter(){
		FilterRegistrationBean hiddenHttpMethodFilter = new FilterRegistrationBean(new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addUrlPatterns("/*");
        return hiddenHttpMethodFilter;
    }
    
    
    
    @Bean
    public MessageChannel employeeChannel() {
    	return new DirectChannel();
    }
    
   
    @Bean
    public IntegrationFlow employeeEvents(EventLogger logger) {
        return IntegrationFlows
        		.from(employeeChannel())
        		.<HREmployeeEvent>handle((p, h) -> {
        			logger.handleHREmployeeEvent(p);
        			return null;
        		})
                .get();
    }
    
    @Bean
    public MessagingTemplate messagingTemplate() {
    	return new MessagingTemplate(employeeChannel());
    }

    


	

}
