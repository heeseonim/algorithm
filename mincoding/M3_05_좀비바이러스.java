import java.io.*;
import java.util.*;

public class M3_05_좀비바이러스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[][] arr;
	static Queue<Point> queue;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		queue = new LinkedList<>();
		st = new StringTokenizer(br.readLine(), " ");
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int sr = Integer.parseInt(st.nextToken()) - 1;
		arr[sr][sc] = 3;
		queue.add(new Point(sr, sc));

		bfs();

		int max = Integer.MIN_VALUE;
		int not = 0;

		for (int[] ar : arr) {
			for (int a : ar) {
				if (a == 1)
					not++;
				if (max < a)
					max = a;
			}
		}

		sb.append(max).append('\n').append(not);
		System.out.println(sb);
	}

	public static void bfs() {
		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < dir.length; i++) {
				int nr = p.r + dir[i][0];
				int nc = p.c + dir[i][1];

				if (!isIn(nr, nc) || arr[nr][nc] != 1)
					continue;

				arr[nr][nc] = arr[p.r][p.c] + 1;
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
