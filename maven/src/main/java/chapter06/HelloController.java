package chapter06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value = "hello.do", method = RequestMethod.GET)
//	@RequestMapping("/hello.do") // @RequestMapping은 get이든 post든 다 됨.
	public String hello(Model model) {
		model.addAttribute("name", "손흥민");
		return "hello"; // <-- [WEB-INF/view/]hello[.jsp]
	}

	// @GetMapping은 get 방식, @PostMapping은 post 방식
	// @GetMapping, @PostMapping은 주소가 같이도 에러가 안남.

	@PostMapping("hello2.do")
	public String hello2(Model model) {
		model.addAttribute("name", "이강인");
		return "hello";
	}

	@GetMapping("hello2.do")
	public String hello3(Model model) {
		model.addAttribute("name", "이강인");
		return "hello";
	}

	// 대부분 @RequestMapping을 쓴다.
	// 보안상 @PostMapping을 꼭 써야할 때가 있음.
	// 완벽하진 않지만 1차적인 방어 => /board/insert?title=aaa&content=bbb 이렇게 글이 작성되거나 하지 않도록
}
