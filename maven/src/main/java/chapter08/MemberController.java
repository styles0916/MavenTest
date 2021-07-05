package chapter08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("/member/index.do")
	public String index(Model model) {
		List<MemberVo> list = (List<MemberVo>) memberService.selectList();
		model.addAttribute("list", list);
		return "member/index";
	}

	@RequestMapping("/member/form.do")
	public String form(MemberVo vo, @CookieValue(value="cookieEmail", required = false) Cookie cookie) {
		if (cookie != null) {
			vo.setEmail(cookie.getValue());
			vo.setCheckEmail("check");
		}
		return "member/form";
	}

	@RequestMapping("/member/login.do")
	public String login(HttpServletRequest req, HttpSession session, HttpServletResponse res, MemberVo vo)
			throws IOException { // form.do에서 email과 pwd를 받아서 vo객체에 저장

		MemberVo member = memberService.login(vo);
		
		if (member == null) {

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('이메일과 비밀번호가 올바르지 않습니다.');");
			out.print("location.href='form.do';");
			out.print("</script>");

			return null;

		} else {

			/*
			 * ★★★세션에 저장★★★ 세션객체 가져오는 방법 1. HttpServletRequest 2. 매개변수로 HttpSession
			 */

			/*
			 * HttpSession session = req.getSession(); getSession(true) : 세션이 존재하지 않으면 새로
			 * 생성해서 리턴 getSession(false) : (default) 세션이 존재하지 않으면 null을 리턴
			 * session.setAttribute("loginInfo", member);
			 */
			session.setAttribute("memberInfo", member);
			
			Cookie cookie = new Cookie("cookieEmail", vo.getEmail());
			cookie.setPath("/");
			if ("check".equals(vo.getCheckEmail())) {
				cookie.setMaxAge(60 * 60 * 24 * 365);
			} else {
				cookie.setMaxAge(0);
			}
			
			res.addCookie(cookie);
			
			return "redirect:index.do";

			/*
			 * MBTI검사처럼 페이지를 이동할 때 데이터를 계속 전송시키면서 진행해야 할 경우, Session을 사용할 수 있다.
			 * 
			 * 1페이지 10문제, 2페이지 10문제, 3페이지 10문제 후, 재출이라고 한다면, 마지막 페이지에는 10문제의 데이터 밖에 없기 때문에
			 * 
			 * 1. Session을 사용해서 모든 데이터를 보관하도록하거나, 2. <input type="hidden"> 을 사용해서 데이터를 옮기면서
			 * 1페이지 10문제 값을 2페이지에, 2페이지 총 20문제 값을 3페이지로 이동시켜서 처리할 수 있다.
			 */
		}
	}

	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, HttpServletResponse res, Model model) throws IOException {

		session.invalidate();

		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "form.do");

		return "include/alert";
	}

	@RequestMapping("/member/mypage.do")
	public String myPage(Model model, HttpSession session) {
		MemberVo vo = (MemberVo) session.getAttribute("memberInfo");
		int mno = vo.getMno();
		MemberVo member = memberService.mypage(mno);
		model.addAttribute("member", member);

		return "member/mypage";
	}

//	@RequestMapping("/member/mypage.do") // 정석대로하면 이렇게 할 수도 있지만 너무 복잡해짐.
//	public String myPage(Model model, HttpSession session) {
//		return service.myPage(session, model);
//	}

	@RequestMapping("/member/update.do")
	public String update(MemberVo vo, Model model) {
		int result = memberService.update(vo);
		if (result == 0) {
			model.addAttribute("msg", "정보변경에 실패했습니다.");
			model.addAttribute("url", "mypage");
			return "include/alert";
		} else {
			model.addAttribute("msg", "정보변경에 성공했습니다.");
			model.addAttribute("url", "index.do");
			return "include/alert";
		}
	}
}