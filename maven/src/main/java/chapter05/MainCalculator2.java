package chapter05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainCalculator2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		Calculator cal = ctx.getBean("calc1", Calculator.class);
		System.out.println("결과 : " + cal.factorial(25)); // $Proxy17에 있는 메서드
		System.out.println(cal.getClass().getName()); // com.sun.proxy.$Proxy17

		// @Aspect가 선언된 객체가 @Configuration이 선언된 객체에 의해서 생성이 될 때,
		// @EnableAspectJAutoProxy가 @Configuration과 함께 선언되어 있으면,
		// @Aspect가 선언된 객체는 Bean이 아닌 Aspect로 생성되고,
		// @Aspect가 선언된 객체의 설정에 따라 해당 기능(Proxy)이 실행 된다.
		// A 메서드가 실행 될 때 해당 기능(Proxy)이 실행되는 경우,
		// A 메서드를 실행하는 객체는 A 메서드를 가진 객체가 아니라, 임의로 생성된 객체에 의해서 실행된다.
		// ex) A 메서드를 가진 객체에서 A 메서드를 이동 -> 임의의 객체 <- @Aspect가 선언된 객체에서 설정된 Proxy를 이동
	}
}
