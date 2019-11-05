import java.io.BufferedReader;
import java.io.InputStreamReader;

public class magicSquare {
	public static int N;
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		map[0][N / 2] = 1;
		// 출발점
		int r = 0; // 행
		int c = N / 2; // 열
		for (int i = 2; i <= N * N; i++) {
			if ((i - 1) % N == 0) { // 넣은 수가 N의 배수이면
				r++; // 아래로 이동
				if (r == N) // 마지막 행이면
					r = 0; // 첫번째 행으로 이동
			} else { // N의 배수가 아니면 왼쪽 위로 이동
				r--;
				c--;
				if (r < 0)
					r = N - 1;
				if (c < 0)
					c = N - 1;
			}
			map[r][c] = i;
		}

		// 완성된 마방진
		for (int[] a : map) {
			for (int b : a) {
				System.out.printf("%2d ", b);
			}
			System.out.println();
		}

		System.out.println("마방진인가요? : " + check());
		System.out.println("임의의 한 행 값 : " + rowSum());
		System.out.println("마방진의 총합은? : " + total());

	}

	// 마방진인지 체크하는 메서드 - 행의합, 열의합, 대각선의합 체크
	public static boolean check() {
		int l = leftDiaSum();
		int r = rightDiaSum();
		if (l != r)
			return false;

		int a = rowSum();
		for (int i = 0; i < N; i++) {
			int b = colSum(i);
			if (a != b || b != l) {
				return false;
			}
		}

		return true;
	}

	// 임의의 한 행의 값을 구하는 메서드
	public static int rowSum() {
		int temp = ((N*N*N)+N)/2;
		return temp;
	}

	// 임의의 한 열의 값을 구하는 메서드
	public static int colSum(int n) {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			temp += map[i][n];
		}
		return temp;
	}

	// 대각선의 합의 값을 구하는 메서드
	public static int leftDiaSum() {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			temp += map[i][i];
		}
		return temp;
	}

	public static int rightDiaSum() {
		int temp = 0;
		int c = N - 1;
		for (int i = 0; i < N; i++) {
			temp += map[i][c--];
		}
		return temp;
	}

	// 정사각형 마방진의 총합을 구하는 메서드
	public static int total() {
		return N * rowSum();
	}
}
