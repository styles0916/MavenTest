package chapter04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"chapter04"})
public class AppCtx {
	
//	@Bean
//	@Qualifier("dao")
//	public MemberDao memberDao1() {
//		return new MemberDao();
//	}
//	
//	@Bean
//	public MemberDao memberDao2() {
//		return new MemberDao();
//	}

	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService();
	}

	@Bean
	public ChangePasswordService pwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
//		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

	@Bean
	public MemberListService listSvc() {
		return new MemberListService();
	}

	@Bean
	public MemberSelectOneService selectOne() {
		MemberSelectOneService selectOne = new MemberSelectOneService();
//		selectOne.setMemberDao(memberDao());
		return selectOne;
	}
}
