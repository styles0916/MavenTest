package chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberSelectOneService {
	@Autowired
	@Qualifier("dao")
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	public Member selectOne(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		return member;
	}
}
