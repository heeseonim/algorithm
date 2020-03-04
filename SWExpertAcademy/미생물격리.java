import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_2382_미생물격리 {
	public static int N, M, K;
	public static micro[][] map;
	public static micro[][] copyMap;
	public static micro[][] maxArr;
	public static int[][] dir = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static class micro {
		int num;
		int dir;

		public micro() {
			this.num = 0;
			this.dir = 0;
		}

		public micro(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new micro[N][N];
			copyMap = new micro[N][N];

			// 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new micro();
					copyMap[i][j] = new micro();
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[r][c] = new micro(num, dir); // 해당 위치에 미생물 수와 방향 정보를 저장
			}

			while (M > 0) {		
				maxArr = new micro[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						maxArr[i][j] = new micro();
					}
				}
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j].num > 0) {
							move(i, j);
						}
					}
				}

				// copyMap 복사 & 초기화
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j].num = copyMap[i][j].num;
						map[i][j].dir = copyMap[i][j].dir;
						copyMap[i][j] = new micro();
					}
				}

				M--;
			} // end of while

			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += map[i][j].num;
				}
			}

			System.out.println("#" + tc + " " + sum);
		}
	} // end of main

	public static void move(int r, int c) {
		int n = map[r][c].num;
		int d = map[r][c].dir;

		int newR = r + dir[d][0];
		int newC = c + dir[d][1];
		if (isIn(newR, newC)) {
			// 약품처리구역이라면
			if (newR == 0 || newR == N - 1 || newC == 0 || newC == N - 1) {
				copyMap[newR][newC].num = n / 2;
				copyMap[newR][newC].dir = calcDir(d); // 방향 반대로
			} else {
				// 해당자리로 더해지는 미생물 수 최댓값과 그 방향 저장하기
				if (maxArr[newR][newC].num < n) { // 지금 변경하려는 값이 더 크다면
					maxArr[newR][newC].num = n;
					maxArr[newR][newC].dir = d;
					copyMap[newR][newC].dir = d; // 방향 변경
				}
				copyMap[newR][newC].num += n;
			}

		}

	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static int calcDir(int dir) {
		if (dir == 1)
			return 2;
		else if (dir == 2)
			return 1;
		else if (dir == 3)
			return 4;
		else
			return 3;
	}
} // end of class
