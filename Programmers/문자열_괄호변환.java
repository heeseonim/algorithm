package 연습;

import java.util.Stack;

public class Solution_프로그래머스_60058_괄호변환 {
	public static void main(String[] args) {
		System.out.println(solution("()))((()"));
	}

	public static String u, v;

	public static String solution(String p) {
		if (p.equals("") || check(p)) // 올바른 괄호 문자열이라면 바로 return
			return p;

		// 올바른 괄호 문자열이 아니라면, 균형잡힌 문자열 두개로 분리
		String u = "";
		String v = "";
		int lcnt = 0, rcnt = 0;
		for (int i = 0; i < p.length(); i++) {
			// 개수를 세서 같아지면 분리
			if (p.charAt(i) == '(')
				lcnt++;
			else
				rcnt++;
			if (lcnt == rcnt) {
				u = p.substring(0, lcnt + rcnt);
				v = p.substring(lcnt + rcnt);
				break;
			}
		}

		String answer = "";
		if (check(u)) { // 분리한 문자열 u가 올바른 괄호 문자열이라면
			answer += u;
			answer += solution(v); // 재귀
		} else { // u가 올바른 괄호 문자열이 아니라면
			StringBuilder sb = new StringBuilder();
			sb.append('(').append(solution(v)).append(')');

			String temp = u.substring(1, u.length() - 1); // u 의 첫번째와 마지막 문자 제거

			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) == '(')
					sb.append(')');
				else
					sb.append('(');
			}

			answer = sb.toString();
		}
		return answer;
	}

	// 올바른 괄호 문자열 체크
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
