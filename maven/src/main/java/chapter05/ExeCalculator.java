package chapter05;

public class ExeCalculator implements Calculator { // => 프록시(Proxy)
	private Calculator delegrate;

	public ExeCalculator(Calculator delegrate) { // Calculator를 상속한 어느 클래스든지 올 수 있다.
		this.delegrate = delegrate;
	}

	@Override
	public long factorial(long num) {
		long start = System.nanoTime();

		long result = delegrate.factorial(num);

		long end = System.nanoTime();
		System.out.println("Exe : " + (end - start));

		return result;
	}
}
