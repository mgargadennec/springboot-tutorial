package fr.mgargadennec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import fr.mgargadennec.tools.HelloWorld;
import fr.mgargadennec.tools.HelloWorldPrinter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class);
		
		System.out.println("Listing available beans");
		
		for(String beanName : context.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
		
	}
	
	@Bean
	public String hello(){
		return "hello";
	}
	
	@Bean
	public String world(){
		return "world";
	}

	@Bean
	public HelloWorld helloWorld(){
		return new HelloWorld(hello(), world());
	}
	
	@Bean(name="fooBar")
	public HelloWorld someRandomName(){
		return new HelloWorld("foo","bar");
	}
	
	@Bean
	public HelloWorldPrinter printer(){
		return new HelloWorldPrinter(helloWorld());
	}
}
