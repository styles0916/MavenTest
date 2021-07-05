package chapter07;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chapter07" })
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

}
