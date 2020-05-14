import java.util.Stack;

public class 일이사나라의숫자 {
	public static void main(String[] args) {
		System.out.println(solution(20));
	}

	public static String solution(int n) {
		Stack<Integer> stack = new Stack<>();
		
		while (n > 0) {
			int nmg = n % 3;
			n = n / 3;

			if (nmg == 0) {
				nmg = 4;
				n -= 1;
			}

			stack.add(nmg);
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty())
			sb.append(stack.pop());

		return sb.toString();
	}
}
