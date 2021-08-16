package chapter12;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Getter // geRtter 메서드
@Setter // setter 메서드
public class MemberVo {

	private int mno;
	private String mname;
	private String email;
	private String pwd;
	private String checkEmail;

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		// db에 암호화해서 저장을 하지 않았기 때문에, (DB에서 하는 암호화 말고, 시큐리티가 제공하는 암호화)
		// getPassword에서 암호화 처리 후, 리턴하도록 임시 수정
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		return pe.encode(pwd);
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCheckEmail() {
		return checkEmail;
	}

	public void setCheckEmail(String checkEmail) {
		this.checkEmail = checkEmail;
	}

}
