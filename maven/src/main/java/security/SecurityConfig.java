package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// 비밀번호 암호화 하는 객체를 Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 사용자 로그인 처리를 위한 객체를 주입 / 암호화
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// 설정
	@Override
	public void configure(HttpSecurity http) throws Exception {
		System.out.println("security 설정");
		http.authorizeRequests()
			.antMatchers("/member/index.do").permitAll() // 저 url은 전부 허용하겠다는 뜻
			.antMatchers("/member/write.do").hasRole("USER") // USER 라는 롤을 가져야만 접속 가능
			.anyRequest().authenticated() // 나머지 요청은 거부
			// security context에서 authentication 객체가 있는지 찾아보고 없으면 인증확인페이지(로그인페이지)로 이동
		.and()
			.formLogin() // 로그인 설정, 이렇게만하면 기본적으로 제공하는 폼이 뜬다.
			.loginPage("/member/form.do") // 로그인폼 페이지
			.usernameParameter("email") // 파라미터명 설정, 기본 값은 username
			.passwordParameter("pwd") // 파라미터명, 기본 값은 password
			.loginProcessingUrl("/auth") // 로그인 처리하는 URL (스프링에서 처리, 이름은 마음대로 지정)
			.failureForwardUrl("/member/loginError.do?msg=idFail") // 로그인 실패시 이동되는 URL
			.defaultSuccessUrl("/member/index.do") // 로그인 성공시 이동되는 URL
			// 로그인 성공시, security context에 authentication 객체 생성 후, 원래 가려고했던 페이지로 이동
			.permitAll()
		.and()
			.logout() // 로그아웃 설정
			.logoutUrl("/member/logout.do") // 로그아웃 처리 URL (스프링에서 처리, 컨트롤러에 직접 코딩하거나 할 필요 없음)
			.invalidateHttpSession(true) // 세션을 모두 삭제
			.logoutSuccessUrl("/member/index.do") // 로그아웃 후, 이동되는 URL
		.and()
			.csrf().disable(); // csrf(토큰배부)를 비활성화
		
		// ex_시큐리티에서 원래 가려고했던 페이지를 저장해두었다가, 로그인 성공하면 가려고했던 페이지로 알아서 이동시켜준다. 
	}
}
