
public class 자물쇠와열쇠 {
	public static void main(String[] args) {
		자물쇠와열쇠 자 = new 자물쇠와열쇠();
		System.out.println(자.solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
				new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }));
	}

	public int[][] copyKey;
	public int N, M;

	public boolean solution(int[][] key, int[][] lock) {
		N = lock.length;
		M = key.length;

		int[][] map = new int[M + 2 * N][M + 2 * N];
		int holeCnt = 0;

		copyKey = new int[M][M];
		for (int i = 0; i < key.length; i++)
			copyKey[i] = key[i].clone();

		for (int i = 0; i < lock.length; i++)
			for (int j = 0; j < lock.length; j++) {
				if (lock[i][j] == 0)
					holeCnt++;

				map[M + i][M + j] = lock[i][j];
			}

		for (int k = 0; k < 4; k++) {

			for (int i = 0; i <= map.length - M; i++)
				for (int j = 0; j <= map[i].length - M; j++) {
					int cnt = 0;
					ex: for (int x = 0; x < M; x++) {
						for (int y = 0; y < M; y++) {
							if (map[i + x][j + y] == 1 && copyKey[x][y] == 1)
								continue ex;

							// 자물쇠안의 홈이 아니라면 넘어가기
							if (M > (i + x) || (i + x) > (M + N - 1) || M > (j + y) || (j + y) > (M + N - 1))
								continue;

							if (map[i + x][j + y] == 0 && copyKey[x][y] == 1)
								cnt++;

						}
					}

					if (holeCnt == cnt)
						return true;

				}

			rotate();
		}

		return false;
	}

	public void rotate() {
		int[][] temp = new int[M][M];

		for (int i = 0; i < M; i++)
			for (int j = 0; j < M; j++)
				temp[j][M - i - 1] = copyKey[i][j];

		copyKey = temp;
	}
}
