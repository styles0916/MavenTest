package chapter06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

	// 별도의 데이터 처리 없이 JSP를 맵핑만 하는 경우는 이렇게 메서드를 만들지 않고,
	// MvcConfig에서 addViewControllers() 메서드를 재정의하여 사용할 수 있다.

//	@RequestMapping("/index.do")
//	public String index() {
//		return "index";
//	}

	// 리턴이 없으면 맵핑된 url과 동일한 경로의 jsp를 자동으로 포워딩 하도록 되어있음.
	// 서블릿으로 응답 가능
	@RequestMapping("/test.do")
	public void test(HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<h1>test</h1>");
		out.println("<script>alert('정상적으로 저장되었습니다.');</script>");
	}

	@RequestMapping("/")
	public String index() {
		return "redirect:index.do";
	}

	@RequestMapping("form.do")
	public String form() {
		return "form";
	}

	@RequestMapping("send.do")
	public String send(Model model, HttpServletRequest req,
			@RequestParam(value = "name", required = false) String name2,
			@RequestParam(value = "email", required = false) String email2,
			@RequestParam(value = "no", required = false, defaultValue = "0") int no2, // 자동 형변환
			MemberVo vo) {

		// ★파라미터를 받는 방법★
		// 1. HttpServletRequest
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		int no = Integer.parseInt(req.getParameter("no")); // 무조건 문자열, 필요 시, 강제 형변환

		// 2. @RequestParam
		// @RequestParam("파라미터명") : 매개변수에 지정

		// 3. 커맨드 객체
		// 값이 많을 경우 1, 2방법보다는 객체에 담아서 사용하는게 훨씬 편하다.
		// 넘어오는 파라미터 이름과 객체에 있는 필드 이름이 같아야함. (setter메서드 호출)

		System.out.println(vo.getHobby().length);

		for (int i : vo.getHobby()) {
			System.out.println(i);
		}

		model.addAttribute("name1", name);
		model.addAttribute("email1", email);
		model.addAttribute("no1", no);

		model.addAttribute("name2", name2);
		model.addAttribute("email2", email2);
		model.addAttribute("no2", no2);

		model.addAttribute("vo", vo); // model에 add하지 않아도, memberVO.name으로 값을 호출 할 수 있다.

		req.setAttribute("id", "son");

		if (email == null || "".equals(email)) {
			return "form";
		}
		return "send";
	}

	@RequestMapping("main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "손흥민");
		mav.setViewName("main");
		return mav;
	}
}
