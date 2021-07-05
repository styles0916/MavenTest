package chapter05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeAspect {
	// 실행 (public 리턴타입 패키지명(하위패키지들까지) 메서드명 (매개변수))
	// .. : 있든 없든
	// * : 모든
	@Pointcut("execution(public * chapter05..factorial(..))")
	private void publicTarget() {
	}

	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("메서드 실행 전!");

		long start = System.nanoTime();

		try {

			Object result = joinPoint.proceed();
			return result;

		} finally {
			System.out.println("메서드 실행 끝!");

			long end = System.nanoTime();
			System.out.println("AOP : " + (end - start));

		}
	}
}
