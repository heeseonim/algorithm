import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17406_배열돌리기4 {
	public static int[][] map;
	public static int[][] copyMap;
	public static int[][] rotation;
	public static int N, M;
	public static int[] arr;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		copyMap = new int[N + 1][M + 1];
		rotation = new int[K][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotation[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령문 개수만큼 배열 생성
		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = i;
		}

		perm(0, K);

		System.out.println(min);

	}

	// 회전을 진행하는 함수
	public static void rotate(int[] cycle) {
		// 회전 진행
		for (int s = cycle[2]; s > 0; s--) {
			int size = s * 2 + 1; // 한 변의 길이
			int row = cycle[0] - s;
			int col = cycle[1] - s;

			int temp = copyMap[row][col]; // 왼쪽 위 값 저장
			// 상
			for (int i = 1; i < size; i++) {
				copyMap[row][col] = copyMap[row + 1][col];
				row++;
			}
			// 좌
			for (int i = 1; i < size; i++) {
				copyMap[row][col] = copyMap[row][col + 1];
				col++;
			}
			// 하
			for (int i = 1; i < size; i++) {
				copyMap[row][col] = copyMap[row - 1][col];
				row--;
			}
			// 우
			for (int i = 1; i < size; i++) {
				copyMap[row][col] = copyMap[row][col - 1];
				col--;
			}
			copyMap[row][col + 1] = temp;
		}
	}

	public static void perm(int step, int K) {
		if (step == K) {
			// 배열 복사
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}

			// 순열로 생성된 배열 전달
			for (int i = 0; i < arr.length; i++) {
				rotate(rotation[arr[i]]); // 회전
			}

			// 행 값 계산
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += copyMap[i][j];
				}
				min = Math.min(sum, min);
			}
		} else {
			for (int i = step; i < K; i++) {
				int temp = arr[step];
				arr[step] = arr[i];
				arr[i] = temp;
				perm(step + 1, K);
				temp = arr[step];
				arr[step] = arr[i];
				arr[i] = temp;
			}
		}
	}
}
