import java.io.*;
import java.util.*;

public class 테트로미노 {
	public static int N, M;
	public static int[][] map;
	public static int answer;
	public static boolean[][] visit;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		answer = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					visit[i][j] = true;
					dfs(i, j, 0, 0);
					visit[i][j] = false;
					T(i, j);
				}
			}
		}

		System.out.println(answer);
	}

	public static void dfs(int r, int c, int depth, int sum) {
		if (depth == 4) { // depth가 4면 테트로미노 완성
			answer = answer < sum ? sum : answer;
			return;
		}

		for (int i = 0; i < dir.length; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (!isIn(nr, nc) || visit[nr][nc])
				continue;

			visit[nr][nc] = true;
			dfs(nr, nc, depth + 1, sum + map[nr][nc]);
			visit[nr][nc] = false;
		}
	}

	// ㅜ 모양의 가운데 좌표 r, c 를 이용해 비교
	public static void T(int r, int c) {
		// 꼭짓점인 경우
		if ((r == 0 || r == N - 1) && (c == 0 || c == M - 1))
			return;

		int sum = 0;
		// 테두리인 경우
		if (r == 0) {
			sum = map[r][c] + map[r][c - 1] + map[r][c + 1] + map[r + 1][c];
		} else if (r == N - 1) {
			sum = map[r][c] + map[r][c - 1] + map[r][c + 1] + map[r - 1][c];
		} else if (c == 0) {
			sum = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c + 1];
		} else if (c == M - 1) {
			sum = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1];
		}

		// 일반적인 경우
		else {
			int one = map[r][c] + map[r][c - 1] + map[r][c + 1] + map[r + 1][c];
			int two = map[r][c] + map[r][c - 1] + map[r][c + 1] + map[r - 1][c];
			int three = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c + 1];
			int four = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1];
			sum = Math.max(Math.max(one, two), Math.max(three, four));
		}

		answer = answer < sum ? sum : answer;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
