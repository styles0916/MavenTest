package chapter06;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.12.3

@RestController // 이 어노테이션을 쓰고 아래에서 객체를 리턴만 해줘도 JSON형태로 알아서 출력해준다.
public class JsonController {

	@GetMapping("/api/test")
	public MemberVo test() {
		MemberVo vo = new MemberVo();
		vo.setName("손흥민");
		vo.setEmail("son@gmail.com");
		vo.setNo(7);
		return vo;
	}

	// 파라미터를 받는 4번째 방법
	// @PathVariable
	// /api/list/1 -> 1페이지
	// /api/list/2 -> 2페이지

	@GetMapping("/api/list/{page}/{searchWord}")
	public List<MemberVo> list(@PathVariable int page, @PathVariable String searchWord) {

		System.out.println("page : " + page);
		System.out.println("searchWord : " + searchWord);

		MemberVo vo = new MemberVo();
		vo.setName("손흥민");
		vo.setEmail("son@gmail.com");
		vo.setNo(7);
		List<MemberVo> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		return list;
	}
}
