package chapter08;

import java.util.List;

public interface MemberService {

	List<MemberVo> selectList();
	MemberVo login(MemberVo vo);
	MemberVo mypage(int mno);
//	String myPage(HttpSession session, Model model);
	int update(MemberVo vo);
	
}
