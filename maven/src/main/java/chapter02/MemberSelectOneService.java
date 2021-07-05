package chapter02;

public class MemberSelectOneService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member selectOne(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		return member;
	}
}
