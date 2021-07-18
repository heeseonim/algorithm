import java.io.*;
import java.util.*;

public class 구현_14503_로봇청소기_2 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int curDir, answer;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		move(r, c, d);
		check();
		System.out.println(answer);
	}

	static void move(int r, int c, int d) {
		Queue<Point> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new Point(r, c, d));

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curDir = cur.dir;

			boolean flag = false; // 4번 돌 때의 상태를 확인하기 위한 변수
			for (int i = 0; i < 4; i++) { // 4번만 돌기
				// 왼쪽으로 회전
				curDir = (curDir + 3) % 4;
				int nr = cur.x + dir[curDir][0];
				int nc = cur.y + dir[curDir][1];

				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 1)
					continue;

				visited[nr][nc] = true;
				flag = true; // 성공~
				q.add(new Point(nr, nc, curDir));
				break;
			}

			if (!flag) { // 아무데도 못갔으면...
				int bd = (curDir + 2) % 4;
				int br = cur.x + dir[bd][0];
				int bc = cur.y + dir[bd][1];
				if (map[br][bc] == 0) { // 후진 가능?
					visited[br][bc] = true;
					q.add(new Point(br, bc, curDir)); // **현재 방향**을 유지한채로 후진***
				}
			}
		}
	}

	static void check() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (visited[i][j])
					answer++;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Point {
		int x, y, dir;

		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
