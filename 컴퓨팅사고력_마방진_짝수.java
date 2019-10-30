import java.io.BufferedReader;
import java.io.InputStreamReader;

public class magicSquare_even {
	public static int N;
	public static int[][] map;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("4의 배수를 입력하세요.");
		N = Integer.parseInt(br.readLine());
		if (N % 4 != 0) {
			System.out.println("다시 입력해주세요.");
			return;
		}
		map = new int[N][N];
		visited = new boolean[N][N];

		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = num++;
			}
		}

		fix(); // 바꾸지 않을 값 고정
		change(); // 원점대칭으로 값 바꾸기

		for (int[] a : map) {
			for (int b : a) {
				System.out.printf("%2d ", b);
			}
			System.out.println();
		}

		System.out.println("마방진인가요? : " + check());
		System.out.println("임의의 한 행 값 : " + rowSum(0));
		System.out.println("마방진의 총합은? : " + total());

	}

	public static void fix() {
		int small = N / 4;
		int big = N / 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i < small && j < small)
					visited[i][j] = true;
				else if (i < small && small + big <= j)
					visited[i][j] = true;
				else if (small + big <= i && j < small)
					visited[i][j] = true;
				else if (small + big <= i && small + big <= j)
					visited[i][j] = true;
				else if (small <= i && i < small + big && small <= j && j < small + big)
					visited[i][j] = true;
			}
		}
	}

	public static void change() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					int newI = -i + (N - 1);
					int newJ = -j + (N - 1);
					int temp = map[i][j];
					map[i][j] = map[newI][newJ];
					map[newI][newJ] = temp;
					visited[i][j] = true;
					visited[newI][newJ] = true;
				}
			}
		}
	}

	// 마방진인지 체크하는 메서드 - 행의합, 열의합, 대각선의합 체크
	public static boolean check() {
		int l = leftDiaSum();
		int r = rightDiaSum();
		if (l != r)
			return false;

		for (int i = 0; i < N; i++) {
			int a = rowSum(i);
			int b = colSum(i);
			if (a != b || b != l) {
				return false;
			}
		}

		return true;
	}

	// 임의의 한 행의 값을 구하는 메서드
	public static int rowSum(int n) {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			temp += map[n][i];
		}
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
		return N * rowSum(0);
	}

}
