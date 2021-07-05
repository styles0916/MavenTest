package chapter06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

	/*
	 * <param-name>contextConfigLocation</param-name>
	 * <param-value>chapter06.MvcConfig chapter06.ControllerConfig</param-value>
	 * 
	 * web.xml에서 삭제. MvcConfig에서 처리.
	 */

	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
