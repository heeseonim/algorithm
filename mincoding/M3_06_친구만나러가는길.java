import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class M3_06_친구만나러가는길 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M, sum;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<Point> queue;
	static Point cheeze, friend;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		N = 3;
		M = 5;
		map = new int[N][M];
		map[1][0] = 1;
		map[1][2] = 1;
		map[0][4] = 1;
		map[2][4] = 1;

		queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		sum = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		cheeze = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		bfs(cheeze);
		queue.removeAll(queue);
		
		st = new StringTokenizer(br.readLine(), " ");
		friend = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		map = new int[N][M];
		map[1][0] = 1;
		map[1][2] = 1;
		map[0][4] = 1;
		map[2][4] = 1;
		
		queue.add(cheeze);
		bfs(friend);
		
		System.out.println(sum);
	}

	public static void bfs(Point cf) {
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			if (p.r == cf.r && p.c == cf.c) {
				sum += map[p.r][p.c];
				return;
			}

			for (int i = 0; i < dir.length; i++) {
				int nr = p.r + dir[i][0];
				int nc = p.c + dir[i][1];

				if (!isIn(nr, nc) || map[nr][nc] != 0) {
					continue;
				}

				map[nr][nc] = map[p.r][p.c] + 1;
				queue.add(new Point(nr, nc));
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
