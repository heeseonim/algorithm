import java.util.Arrays;

public class Solution_42748 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(
				solution(new int[] { 1, 5, 2, 6, 3, 7, 4 }, new int[][] { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } })));
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		// commands = i번째 숫자부터, j번째 숫자까지 자르고, k번째에 있는 수 구함
		// commands[0] = { 2, 5, 3 }
		for (int i = 0; i < commands.length; i++) {
			int[] cut = new int[commands[i][1] - commands[i][0] + 1]; // 자르는 공간 할당
			int index = 0;

			// ex) 2번째부터 5번째까지 (1~4)
			for (int j = commands[i][0] - 1; j < commands[i][1]; j++) {
				cut[index++] = array[j];
			}
			Arrays.sort(cut); // 자른 배열 정렬

			answer[i] = cut[commands[i][2] - 1]; // k번째 수 저장
		}
		return answer;
	}
}
