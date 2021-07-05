package chapter08;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public List<MemberVo> selectList() {
		return memberDao.selectList();
	}

	@Override
	public MemberVo login(MemberVo vo) {
		return memberDao.login(vo);
	}

	@Override
	public MemberVo mypage(int mno) {
		return memberDao.selectOne(mno);
	}

//	@Override
//	public String myPage(HttpSession session, Model model) {
//		MemberVo vo = (MemberVo) session.getAttribute("memberInfo");
//		int mno = vo.getMno();
//		MemberVo member = memberDao.selectOne(mno);
//		model.addAttribute("member", member);
//
//		return "member/myPage";
//	}

	@Override
	public int update(MemberVo vo) {
		return memberDao.update(vo);
	}

}
