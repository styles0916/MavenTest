package chapter07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/member/index.do")
	public String index(Model model) {
		List<MemberVo> list = (List<MemberVo>) memberService.selectList();
		model.addAttribute("list", list);
		return "/member/index";
	}
}
