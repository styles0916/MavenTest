package chapter06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("test")
public class TestController {
	
	@RequestMapping("test/form.do")
	public String form() {
		return "form";
	}
}
