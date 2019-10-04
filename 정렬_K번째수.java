import java.util.Arrays;

public class 정렬_K번째수 {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
		System.out.println(Arrays.toString(solution(array, commands)));
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length]; // 명령문의 개수만큼 생성

		for (int i = 0; i < commands.length; i++) { // 명령문의 개수만큼 돌면서
			int start = commands[i][0] - 1; // 시작 좌표
			int end = commands[i][1]; // 끝 좌표 (반복문 돌릴 때 포함 안되므로 빼주지 않음)
			int[] arr = new int[end - start];
			int index = 0;
			for (int k = start; k < end; k++) {
				arr[index++] = array[k];
			}
			Arrays.sort(arr); // 새로 구해진 배열 정렬
			answer[i] = arr[commands[i][2] - 1]; // 명령문의 위치에 있는 원소 저장
		}

		return answer;
	}
}
