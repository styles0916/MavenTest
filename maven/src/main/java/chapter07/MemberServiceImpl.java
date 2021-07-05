package chapter07;

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

}
