package _PRACTICE;

import java.io.*;
import java.util.*;

public class 연구소3 {
	public static int N, M;
	public static int[][] map;
	public static int[][] virusMap;
	public static Vector<Point> list;
	public static boolean[][] visited;
	public static Queue<Point> queue;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int[] trr;
	public static int max, answer;

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new Vector<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) { // 바이러스일 경우 벡터에 담음
					list.add(new Point(i, j));
				}
			}
		}

		trr = new int[M];
		answer = Integer.MAX_VALUE; // 완료시간 중 최솟값

		comb(list.size(), M);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	public static void comb(int n, int r) {
		if (r == 0) {
			virusMap = new int[N][N];
			visited = new boolean[N][N];
			queue = new LinkedList<>(); // bfs 에 사용될 큐
			for (int t : trr) {
				Point p = list.get(t);
				queue.add(new Point(p.x, p.y));
				visited[p.x][p.y] = true;
			}
			bfs();
		} else if (n < r) {
			return;
		} else {
			trr[r - 1] = n - 1;
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}
	}
	
	public static void bfs() {
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int nr = p.x + dir[i][0];
				int nc = p.y + dir[i][1];

				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) // 범위를 넘거나, 방문했거나, 벽이면 넘어감
					continue;

				virusMap[nr][nc] = virusMap[p.x][p.y] + 1;
				queue.add(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}

		if (check()) { // 바이러스가 다 퍼졌다면
			// 총 시간 구하기
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 2) // 비활성->활성된 바이러스 자리는 체크 X, 바이러스자리에는 퍼지지 않아도 상관없음
						continue;
					max = max < virusMap[i][j] ? virusMap[i][j] : max;
				}
			answer = answer > max ? max : answer;
		}
	}

	public static boolean check() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == 0 && virusMap[i][j] == 0) // 빈칸에 변화가 없다면 false
					return false;
		return true;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
