package chapter03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
	private static AnnotationConfigApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("[ 명령어를 입력해주세요. ]");

			String cmd = br.readLine();
			if (cmd.toLowerCase().equals("exit")) {
				System.out.println("[ 종료합니다. ]");
				break;
			} else if (cmd.startsWith("new")) {
				processNewCommand(cmd.split(" "));
			} else if (cmd.startsWith("change")) {
				processChangeCommand(cmd.split(" "));
			} else if (cmd.toLowerCase().equals("list")) {
				processListCommand();
			} else if (cmd.startsWith("info")) {
				processSelectOneCommand(cmd.split(" "));
			}
		}
	}

	private static void processNewCommand(String[] args) {
		if (args.length != 5) {
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);

		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록 완료");
		} catch (DuplicateMemberException e) {
			System.out.println("이메일 중복");
		}
	}

	private static void processChangeCommand(String[] args) {
		if (args.length != 4) {
			return;
		}
		ChangePasswordService pwdSvc = ctx.getBean("pwdSvc", ChangePasswordService.class);
		String email = args[1];
		String oldPwd = args[2];
		String newPwd = args[3];
		try {
			pwdSvc.changePassword(email, oldPwd, newPwd);
			System.out.println("비밀번호 변경 완료");
		} catch (MemberNotFoundException e) {
			System.out.println("회원이 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}
	}

	private static void processListCommand() {
		MemberListService listSvc = ctx.getBean("listSvc", MemberListService.class);
		Map<String, Member> map = listSvc.printList();
		Set<String> set = map.keySet();

		for (String key : set) {
			Member member = map.get(key);
			System.out.println(member.getId() + " / " + member.getEmail() + " / " + member.getPassword() + " / "
					+ member.getName() + " / " + member.getRegisterDateTime());
		}
		System.out.println();
	}

	private static void processSelectOneCommand(String[] args) {
		if (args.length != 2) {
			return;
		}
		MemberSelectOneService selectOne = ctx.getBean("selectOne", MemberSelectOneService.class);
		try {
			Member member = selectOne.selectOne(args[1]);
			System.out.println("id : " + member.getId() + " / email : " + member.getEmail() + " / password : "
					+ member.getPassword() + " / name : " + member.getName() + " / regDate : "
					+ member.getRegisterDateTime());
		} catch (MemberNotFoundException e) {
			System.out.println("회원이 존재하지 않습니다.");
		}
	}
}
