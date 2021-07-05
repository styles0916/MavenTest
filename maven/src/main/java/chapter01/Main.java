package chapter01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class); // greeter라는 이름으로 빈이 등록
		
//		Greeter greeter = (Greeter) ctx.getBean("greeter");
		Greeter greeter = ctx.getBean("greeter", Greeter.class);
		
		System.out.println(greeter.greet("손흥민"));
	}
}
