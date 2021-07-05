package chapter08;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties.InterceptorProperty;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chapter08" })
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("index.do").setViewName("index");
	}

	//////////////////////////////////////////////////

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3307/webdb");
		dataSource.setUsername("webuser");
		dataSource.setPassword("web1234");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath*://mapper/**/*.xml"));
		return ssfb.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}

//////////////////////////////////////////////////

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor()).addPathPatterns("/member/mypage.do").addPathPatterns("/member/update.do");

		/*
		 * ★★★★★
		 *
		 * excludePathPatterns() : 해당 URL은 제외
		 *
		 * ex) 관리자 (로그인 페이지 빼고는 전부 다 로그인 체크 해야함)
		 * /admin/board/index.do
		 * /admin/board/write.do
		 * /admin/board/insert.do
		 * ...
		 * 
		 * => .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do");
		 */
	}
}
