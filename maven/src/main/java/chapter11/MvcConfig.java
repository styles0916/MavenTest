package chapter11;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "chapter11", "security" })
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

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry reg) {
//		reg.jsp("/WEB-INF/view/", ".jsp");
//	}
	
	//////////////////////////////////////////////////

	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.viewResolver(thymeleafViewResolver());
		
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setContentType("text/html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templatEngine = new SpringTemplateEngine();
		templatEngine.setTemplateResolver(templateResolver());
		templatEngine.setEnableSpringELCompiler(true);
		return templatEngine;
	}
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(context);
		templateResolver.setPrefix("/WEB-INF/thymeleaf/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	
	//////////////////////////////////////////////////
	
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
		 * ???????????????
		 *
		 * excludePathPatterns() : ?????? URL??? ??????
		 *
		 * ex) ????????? (????????? ????????? ????????? ?????? ??? ????????? ?????? ?????????)
		 * /admin/board/index.do
		 * /admin/board/write.do
		 * /admin/board/insert.do
		 * ...
		 * 
		 * => .addPathPatterns("/admin/**").excludePathPatterns("/admin/index.do");
		 */
	}
	
	//////////////////////////////////////////////////

	// ???????????? ?????? ?????? Bean
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		// ??????????????? ????????? ????????? ??? ????????? ????????? ?????? ?????? ??????????????? ??????.
		conf.setLocations(new ClassPathResource("db.properties"));
		return conf;
	}
	
	// ??????????????? ????????? Bean
	//	select @@autocommit;
	//	set autocommit = false;
	@Bean
	public PlatformTransactionManager txManager() {
		// ?????? ?????????????????? ????????? ?????? ???????????? ??????
		DataSourceTransactionManager ptm = new DataSourceTransactionManager();
		ptm.setDataSource(dataSource());
		return ptm;
	}
}
