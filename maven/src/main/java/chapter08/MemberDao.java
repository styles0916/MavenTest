package chapter08;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//스프링이 관리해야할 대상
//service가 의존해야하는 대상

@Repository
public class MemberDao {

	@Autowired // 타입이 같은 것을 찾아서 자동 주입 (MvcConfig에서 @Bean으로 등록)
	SqlSessionTemplate sqlSessionTemplate;

	public List<MemberVo> selectList() {
		return sqlSessionTemplate.selectList("member.selectList");
	}
	
	public MemberVo login(MemberVo vo) {
		return sqlSessionTemplate.selectOne("member.login", vo);
	}
	
	public MemberVo selectOne(int mno) {
		return sqlSessionTemplate.selectOne("member.selectOne", mno);
	}
	
	public int update(MemberVo vo) {
		return sqlSessionTemplate.update("member.update", vo);
	}
}
