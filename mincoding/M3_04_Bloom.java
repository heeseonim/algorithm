import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M3_04_Bloom {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] map;
	static boolean[][] check;
	static int h, w, time;
	static Queue<Point> q;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		check = new boolean[h][w];

		q = new LinkedList<>();
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			q.add(new Point(r, c));
			map[r][c] = 1;
			check[r][c] = true;
		}

		time = 1;
		bfs();
		System.out.println(time);
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < dir.length; i++) {
				int nr = p.r + dir[i][0];
				int nc = p.c + dir[i][1];

				if (!isIn(nr, nc) || check[nr][nc])
					continue;

				map[nr][nc] = map[p.r][p.c] + 1;
				check[nr][nc] = true;
				q.add(new Point(nr, nc));
			}
			
			if (isAll())
				return;
		}
	}
	
	public static boolean isAll() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (time < map[i][j])
					time = map[i][j];
				if (!check[i][j])
					return false;
			}
		}
		return true;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w;
	}

	public static class Point {
		int r, c;

		public Point(int x, int y) {
			this.r = x;
			this.c = y;
		}
	}
}
