package security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chapter12.MemberDao;
import chapter12.MemberVo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	MemberDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVo vo = dao.selectOneByEmail(username); // 이메일로 데이터 조회
		
		UserDetailsImpl userDetails = new UserDetailsImpl();
//		userDetails.setUsername(vo.getEmail());
		userDetails.setPassword(vo.getPwd());

		List<GrantedAuthority> authorities = new ArrayList<>();
		// 권한을 체크하기 위해서 필요함
		// DB에서 권한 관리도 가능
		// 권한명은 '반드시' 'ROLE_'로 시작해야한다.
		// 여러개인 경우 add() 추가
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authorities.add(new SimpleGrantedAuthority("ROLE_" + vo.권한컬럼));
		userDetails.setAuthorities(authorities);

		return userDetails;
	}

}
