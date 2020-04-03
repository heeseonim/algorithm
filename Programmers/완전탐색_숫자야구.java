import java.util.*;

public class Solution_42841 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } }));
	}

	public static int solution(int[][] baseball) {
		// 가능한 모든 조합의 수의 배열 만들기 (1~9, 중복X)
		Stack<String> number = new Stack<>();
		for (int i = 1; i < 10; i++)
			for (int j = 1; j < 10; j++)
				for (int k = 1; k < 10; k++) {
					if (i == j || j == k || i == k)
						continue;
					number.add(String.valueOf(i * 100 + j * 10 + k));
				}

		int answer = 0;
		while (!number.isEmpty()) {
			boolean flag = true;
			String cur = number.pop();
			for (int i = 0; i < baseball.length; i++) {
				int s = strike(cur, String.valueOf(baseball[i][0]));
				int b = ball(cur, String.valueOf(baseball[i][0])) - s;
				if (s != baseball[i][1] || b != baseball[i][2])
					flag = false; // 다른 경우가 있다면 false 저장
			}
			if(flag) // 다른 경우가 없다면
				answer++;
		}

		return answer;
	}

	// 현재 숫자와 배열에 들어있는 숫자를 비교하여 스트라이크(위치와 숫자가 일치) 수를 반환
	public static int strike(String num, String target) {
		int count = 0;
		for (int i = 0; i < 3; i++)
			if (num.charAt(i) == target.charAt(i))
				count++;

		return count;
	}

	// 현재 숫자와 배열에 들어있는 숫자를 비교하여 볼(수만 일치) 수를 반환
	public static int ball(String num, String target) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			char c = num.charAt(i);
			for (int j = 0; j < 3; j++)
				if (c == target.charAt(j))
					count++;

		}
		return count;
	}
}
