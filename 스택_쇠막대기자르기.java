import java.util.Scanner;

public class Solution_5432_쇠막대기자르기 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String G = scanner.next();
			int stick = 0, top = -1;

			for (int i = 0; i < G.length(); i++) {
				if (G.charAt(i) == '(') {
					top++;
				} else { // c == ')'
					if (G.charAt(i - 1) == '(') { // 레이저라면
						top--; // 레이저 없애기
						stick += (top + 1); // 스택에 들어있는 '('의 갯수 = 막대기 갯수
					} else { // G.charAt(i-1) == ')' 막대기의 끝이라면
						top--; // 막대기 없애기
						stick++; // 막대기 조각 하나 증가
					}
				}
			}

			System.out.println("#" + tc + " " + stick);
		}
	}
}
