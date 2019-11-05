package practice;

public class Solution_프로그래머스_42895_N으로표현 {
	public static void main(String[] args) {
		System.out.println(solution(5, 31168));
	}

	static int answer = -1;

	public static int solution(int N, int number) {
		dfs(N, number, 0, 0);
		return answer;
	}

	static void dfs(int N, int number, int cnt, int prev) {
		int tempN = N;

		if (cnt > 8) { // cnt가 8보다 커지면 -1 return
			answer = -1;
			return;
		}

		if (number == prev) { // 사칙연산을 수행한 수가 주어진 수와 같아지면
			if (answer == -1 || answer > cnt) // 최솟값 비교하여 answer 에 저장
				answer = cnt;
			return;
		}

		for (int i = 0; i < 8 - cnt; i++) {
			// 네가지의 사칙연산 수행하여 탐색
			dfs(N, number, cnt + i + 1, prev - tempN);
			dfs(N, number, cnt + i + 1, prev + tempN);
			dfs(N, number, cnt + i + 1, prev * tempN);
			dfs(N, number, cnt + i + 1, prev / tempN);

			tempN = change(tempN, N); // 수 붙여주기
		}
	}

	static int change(int tempN, int N) {
		return tempN * 10 + N;
	}

}
