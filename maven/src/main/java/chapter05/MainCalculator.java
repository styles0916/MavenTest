package chapter05;

public class MainCalculator {

	public static void main(String[] args) {

		// ExeCalculator => 프록시(Proxy)

		Calculator calc = new ExeCalculator(new ImplCalculator());
		System.out.println(calc.factorial(10));
		// ★인터페이스의 다형성★

		System.out.println();

		calc = new ExeCalculator(new ImplCalculator2());
		System.out.println(calc.factorial(10));
		// ★인터페이스의 다형성★
	}
}
