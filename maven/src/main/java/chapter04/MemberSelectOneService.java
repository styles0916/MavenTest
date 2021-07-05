package chapter04;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberSelectOneService {
	@Autowired
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
