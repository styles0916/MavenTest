package chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ChangePasswordService {
	@Autowired
	@Qualifier("dao")
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
	}
}
