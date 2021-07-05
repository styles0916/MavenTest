package chapter05;

public class ImplCalculator2 implements Calculator {

	@Override
	public long factorial(long num) {
//		4! => 4 * (4-1) * (3-1) * (2-1) => 4 * 3 * 2 * 1
//		재귀호출
//		long start = System.currentTimeMillis();

		System.out.println("implCalculator2 실행 중!");
		
		try {
			if (num == 0) {
				return 1;
			} else {
				return num * factorial(num - 1);
			}
		} finally { // finally에 넣으면 리턴이 되어도 무조건 실행 됨.
//			long end = System.currentTimeMillis();
//			System.out.println("재귀호출 : " + (end - start));
		}

	}
}
