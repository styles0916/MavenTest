package chapter05;

public class ImplCalculator implements Calculator {

	@Override
	public long factorial(long num) {
//		4! => 4 * (4-1) * (3-1) * (2-1) => 4 * 3 * 2 * 1
		
//		long start = System.currentTimeMillis();

		System.out.println("implCalculator 실행 중!");

		long result = 1; // 곱셈을 하기 때문에 0이면 안됨.

		for (long i = 1; i <= num; i++) {
			result *= i;
		}

//		long end = System.currentTimeMillis();
//		System.out.println("for문 : " + (end - start));
		return result;
	}
}