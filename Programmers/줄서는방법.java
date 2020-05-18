import java.util.*;

public class 줄서는방법 {
	public static void main(String[] args) {
		줄서는방법 문 = new 줄서는방법();
		System.out.println(Arrays.toString(문.solution(3, 5)));
	}

	public int[] solution(int n, long k) {
		int[] answer = new int[n];
		List<Integer> list = new LinkedList<>();
		long factorial = 1;
		for (int i = 1; i <= n; i++) {
			list.add(i);
			factorial *= i; // factorial == n!
		}

		int i = 0;
		long remain = k - 1;
		while (i < n) {
			factorial = factorial / (n - i);
			long value = remain / factorial;

			answer[i++] = list.get((int) value);
			list.remove((int) value);

			remain = remain % factorial;
		}

		return answer;
	}
}
