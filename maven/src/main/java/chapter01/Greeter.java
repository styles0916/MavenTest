package chapter01;

public class Greeter {
	private String format;

	public String greet(String guest) {
		return String.format(format, guest);
	}

	public void setFormat(String format) {
		this.format = format;
	}
//	객체생성 -> format값 넣기 -> greet 메서드 실행
}
