package chapter11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public int insert(MemberVo vo, HttpServletRequest req) throws Exception {

		int result = 0;
		System.out.println("★★insert 전 mno★★" + vo.getMno());
		memberDao.insert(vo);
		System.out.println("★★insert 후 mno★★" + vo.getMno());

		// vo 객체에 mno가 set이 되어있는 상태

		String[] school = req.getParameterValues("school");
		String[] year = req.getParameterValues("year");
		int mno = vo.getMno();

		Map<String, Object> map = new HashMap<>();
		map.put("members_mno", mno);
//		if (true) {throw new Exception();}
		for (int i = 0; i < school.length; i++) {
			if (!"".equals(school[i])) {
				map.put("school", school[i]);
				map.put("year", year[i]);
				memberDao.insertSchool(map);
				result++;
			}
		}
		return result;
	}
}
