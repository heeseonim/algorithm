import java.io.*;
import java.util.*;

public class M4_07_동전던지기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] map;
	static int H, W;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = (str.charAt(j) == '_' ? 1 : 2); // 빈칸이면 1점, 동전이면 2점
			}
		}
		

		int Q = Integer.parseInt(br.readLine());
		int result = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			
			if (type == 1) { // 동전 던지기
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
			} else {
				visited = new boolean[H][W];
				answer = new int[H * W + 1];
				// bfs 실행
				for (int k = 0; k < H; k++) {
					for (int l = 0; l < W; l++) {
						if (visited[k][l])
							continue;
						bfs(k, l);
					}
				}
				result += answer[Integer.parseInt(st.nextToken())];
			}
		}

		System.out.println(result);
	}

	public static void bfs(int k, int l) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(k, l));
		visited[k][l] = true;
		int sum = map[k][l];

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < dir.length; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (!isIn(nr, nc) || map[nr][nc] != map[cur.r][cur.c] || visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				sum += map[nr][nc];
				queue.add(new Point(nr, nc));
			}
		}

		answer[sum]++;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
