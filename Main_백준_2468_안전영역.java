import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2468_안전영역 {

	static int N;
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] visited;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int count = 0;

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copyMap = new int[N][N];
		visited = new boolean[N][N];

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}

		int rain = min; // 최소 높이부터 시작
		int cnt_max = 1; // 아무것도 잠기지 않았을 때 최대 구역의 수는 1
		while (rain != max) { // 강수량이 최대 높이와 같지 않을 때 반복
			
			// 방문체크 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > rain) {
						copyMap[i][j] = 1;
					} else {
						copyMap[i][j] = 0;
					}
				}
			}

			count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[i][j] == 1) {
						bfs(i, j);
						count++;
					}
				}
			}

			cnt_max = Math.max(cnt_max, count);
			rain++;
		}

		System.out.println(cnt_max);

	} // end of main

	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int r = point.x;
			int c = point.y;

			for (int k = 0; k < 4; k++) {
				int newI = r + dir[k][0];
				int newJ = c + dir[k][1];
				if (isIn(newI, newJ) && copyMap[newI][newJ] == 1 && !visited[newI][newJ]) {
					queue.add(new Point(newI, newJ));
					visited[newI][newJ] = true;
					copyMap[newI][newJ] = 0;
				}
			}
		}

	}

	public static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}

} // end of class
