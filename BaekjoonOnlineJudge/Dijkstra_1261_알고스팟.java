import java.io.*;
import java.util.*;

// Dijkstra
// 칸 사이의 가중치를 0, 1로 생각
// 2차원 dist 배열
public class Main {
	public static int N, M;
	public static int[][] arr;
	public static int[][] dist;
	public static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dist = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine(); // 공백없음!
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
			Arrays.fill(dist[i], Integer.MAX_VALUE); // 최댓값 세팅
		}

		dijkstra(0, 0);
		
		System.out.println(dist[N - 1][M - 1]);
	}

	public static void dijkstra(int sx, int sy) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(sx, sy, 0));
		dist[sx][sy] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < dir.length; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if (!isIn(nx, ny))
					continue;
				// 다음 칸의 가중치 > 가지고 있는 최소값 + 다음 칸의 값
				if (dist[nx][ny] > cur.val + arr[nx][ny]) {
					dist[nx][ny] = cur.val + arr[nx][ny];
					q.add(new Point(nx, ny, dist[nx][ny]));
				}
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	public static class Point {
		int x, y, val;

		public Point(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
}
