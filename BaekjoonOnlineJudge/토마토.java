import java.io.*;
import java.util.*;

public class 토마토 {
	static int M, N, H;
	static int[][][] map;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] dz = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[N][M][H];

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		bfs();

		int answer = Integer.MIN_VALUE;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					answer = answer < map[i][j][k] ? map[i][j][k] : answer;
				}
			}
		}

		System.out.println(answer - 1); // 1부터 시작하기 때문에
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j][k] == 1)
						queue.add(new Point(i, j, k));
				}
			}
		}

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			// 상하좌우
			for (int i = 0; i < dir.length; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];
				if (!isIn(nx, ny, p.z) || map[nx][ny][p.z] != 0)
					continue;

				map[nx][ny][p.z] = map[p.x][p.y][p.z] + 1;
				queue.add(new Point(nx, ny, p.z));
			}

			// 앞 뒤
			for (int i = 0; i < dz.length; i++) {
				int nz = p.z + dz[i];
				if (!isIn(p.x, p.y, nz) || map[p.x][p.y][nz] != 0)
					continue;

				map[p.x][p.y][nz] = map[p.x][p.y][p.z] + 1;
				queue.add(new Point(p.x, p.y, nz));
			}

//			for (int k = 0; k < H; k++) {
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(map[i][j][k] + " ");
//					}
//					System.out.println();
//				}
//			}
//			System.out.println();
		}

	}

	public static boolean isIn(int r, int c, int h) {
		return r >= 0 && r < N && c >= 0 && c < M && h >= 0 && h < H;
	}

	public static class Point {
		int x;
		int y;
		int z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
