package chapter02;

import java.util.Map;

public class MemberListService {
	private MemberDao memberDao;
	
	public MemberListService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Map<String, Member> printList() {
		return memberDao.selectList();
	}
}
