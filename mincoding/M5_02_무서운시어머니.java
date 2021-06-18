import java.io.*;
import java.util.*;

public class M5_02_무서운시어머니 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] map;
	static boolean[][] check;
	static int N, min;
	static Point mother, me;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		mother = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new boolean[N][N];

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (max < map[i][j]) {
					max = map[i][j];
					me = new Point(i, j); // 제일 큰 값 인덱스 저장
				}
			}
		}

		min = Integer.MAX_VALUE;
		check[mother.r][mother.c] = true;
		dfs(mother.r, mother.c, map[mother.r][mother.c]);

		System.out.println(min);

	}

	// 시어머니 -> 해당 위치 : 값 더해주기
	public static void dfs(int r, int c, int dist) {
		if (r == me.r && c == me.c) {
			if (min > dist)
				min = dist;
			return;
		}

		for (int i = 0; i < dir.length; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (!isIn(nr, nc) || check[nr][nc] || map[nr][nc] == -1)
				continue;

			check[nr][nc] = true;
			dfs(nr, nc, dist + map[nr][nc]);
			check[nr][nc] = false; // 최소거리 찾기
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
