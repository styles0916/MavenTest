package chapter05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // @Aspect 선언된 것을 적용
public class AppCtx {

	@Bean
	public ExeAspect exeAspect() {
		return new ExeAspect();
	}

	@Bean
	public Calculator calc1() {
		return new ImplCalculator();
	}

	@Bean
	public Calculator calc2() {
		return new ImplCalculator2();
	}

}
