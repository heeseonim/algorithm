import java.io.*;

public class BackTracking_9663_NQueen {
	public static int N, answer;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N]; // [행] = 열
		answer = 0;
		nqueen(0);
		System.out.println(answer);
	}

	public static void nqueen(int row) {
		// 마지막 행까지 왔다면
		if (row == N) {
			answer++;
			return;
		}

		for (int i = 0; i < N; i++) { // N 까지 넣어보기
			arr[row] = i;

			// 이 자리에 들어가도 되는지 검사하기
			if (check(row)) {
				// 이 자리가 괜찮다면 다음 인덱스 진행하기
				nqueen(row + 1);
			}
		}
	}

	public static boolean check(int row) {
		for (int i = 0; i < row; i++) { // 해당 행까지 진행된 열을 검사
			// 같은 열을 가지고 있거나, 대각선에 있다면
			if (arr[row] == arr[i] || row - i == Math.abs(arr[row] - arr[i]))
				return false;
		}
		return true;
	}
}
