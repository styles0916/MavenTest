package chapter01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter01/beans.xml");
		
		Greeter greeter = ctx.getBean("greeter", Greeter.class);
		
		System.out.println(greeter.greet("손흥민"));
	}
	
}
