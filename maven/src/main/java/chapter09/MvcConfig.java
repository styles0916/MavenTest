package chapter09;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chapter09" })
public class MvcConfig implements WebMvcConfigurer {

	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
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
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
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
	
	//////////////////////////////////////////////////

	// 프로퍼티 파일 처리 Bean
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		// 클래스패스 최상위 경로에 둘 것이기 때문에 파일 명만 매개변수로 설정.
		conf.setLocations(new ClassPathResource("db.properties"));
		return conf;
	}
	
	// 트랜잭션을 처리할 Bean
	//	select @@autocommit;
	//	set autocommit = false;
	@Bean
	public PlatformTransactionManager txManager() {
		// 해당 인터페이스를 구현한 객체 생성하여 사용
		DataSourceTransactionManager ptm = new DataSourceTransactionManager();
		ptm.setDataSource(dataSource());
		return ptm;
	}
}
