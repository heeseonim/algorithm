package 연습;

import java.util.Stack;

public class 스택_올바른괄호확인 {
	public static boolean check(String p) {
		Stack<Character> stack = new Stack<>();

		try {
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == '(')
					stack.push('(');
				else // ')' 일 경우 빼줌
					stack.pop();
			}
		} catch (Exception e) { // stack 예외 처리
			return false;
		}

		if (stack.isEmpty())
			return true;

		return false;
	}
}
