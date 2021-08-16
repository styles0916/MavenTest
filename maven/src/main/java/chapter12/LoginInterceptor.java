package chapter12;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	/*
	 * default 메서드
	 * 
	 * preHandle : 컨트롤러 실행 전
	 * postHandle : 컨트롤러 실행 후 (뷰를 리턴하기 전)
	 * afterCompletion : 뷰 실행 후
	 */

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		// Session 객체에서 memberInfo를 가져와서 있으면 (로그인 되어있으면) true, 없으면 false를 리턴

		HttpSession session = req.getSession();
		MemberVo vo = (MemberVo) session.getAttribute("memberInfo");
		if (vo != null) {
			return true;
		} else {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('로그인 후 사용 가능 합니다.');");
			out.print("location.href='index.do';");
			out.print("</script>");
			return false;
		}
	}
}
