import java.io.*;
import java.util.*;

public class DFS_연구소 {
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int N, M;
	public static int[][] map;
	public static int[][] copyMap;
	public static boolean[][] visited;
	public static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static Vector<Point> list;
	public static int[] trr;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new Vector<>();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					list.add(new Point(i, j)); // 0인 자리를 저장
			}
		}

		trr = new int[3];
		comb(list.size(), 3); // 조합 진행

		System.out.println(answer);
	}

	public static void comb(int n, int r) {
		if (r == 0) {
			copyMap = new int[N][M];
			for (int i = 0; i < N; i++)
				copyMap[i] = map[i].clone(); // 맵 복사

			for (int t : trr) {
				Point p = list.get(t);
				copyMap[p.x][p.y] = 1; // 완성된 조합대로 벽을 세움
			}
			
			findVirus();
			
		} else if (n < r)
			return;
		else {
			trr[r - 1] = n - 1;
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}
	}

	public static void findVirus() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == 2) // 바이러스 퍼뜨리기
					dfs(i, j);

		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == 0) // 안전영역 개수 세기
					cnt++;

		answer = answer < cnt ? cnt : answer;
	}

	public static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			// 범위를 벗어나거나, 0이 아니라면 다음으로
			if (!isIn(nr, nc) || copyMap[nr][nc] != 0)
				continue;

			copyMap[nr][nc] = 2; // 바이러스를 넣어주고, dfs 재진행
			dfs(nr, nc);
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
