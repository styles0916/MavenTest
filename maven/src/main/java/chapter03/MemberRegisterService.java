package chapter03;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberRegisterService {
	@Autowired
	@Qualifier("memberDao1")
	private MemberDao memberDao;

//	public MemberRegisterService(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	public Long regist(RegisterRequest req) { // Member 객체를 써도되지만, 커맨드 객체라는 개념 때문에 RegisterRequest 클래스 사용.
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복입니다.");
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
