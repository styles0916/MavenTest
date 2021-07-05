package chapter06;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chapter06" })
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		// webapp에 jsp를 두면 controller를 거치지 않아도 jsp로 접근할 수 있다.
		// 이제부터는 직접 접근하지 못하도록 하기위해 webapp이 아니라 WEB-INF안에 넣어준다.

		// jsp(JSP가 있는 경로[시작경로], 확장자)
		reg.jsp("/WEB-INF/view/", ".jsp");

		// return "member/MemberList" ==> "/WEB-INF/view/member/MemberList.jsp"
	}

	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		// 비지니스 로직이 필요없는 (디자인만 있는 페이지) url과 jsp 매핑
		reg.addViewController("index.do").setViewName("index");
	}
}
