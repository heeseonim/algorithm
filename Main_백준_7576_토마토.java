import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_7576_토마토 {
	static int N, M;
	static int[][] map;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];

		for (int i = 0; i < M; i++) {
			String[] strArr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(strArr[j]);
			}
		}
		
		bfs();

	}

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					queue.add(new Point(i,j)); //1이면 큐에 다 넣어준다.
				}
			}
		}

		//큐가 빌 때까지 큐를 통해 탐색
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int x = point.x;
			int y = point.y;

			for (int k = 0; k < 4; k++) {
				int newX = x + dir[k][0];
				int newY = y + dir[k][1];
				if (isIn(newX, newY) && map[newX][newY] == 0) {
					map[newX][newY] = map[x][y] + 1; //이전 위치에 1을 더해준다.
					queue.add(new Point(newX, newY));
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, map[i][j]);
			}
		}
		System.out.println(max-1); // 처음에 1에 값을 더해주기 때문에 1을 빼줌
	}

	static boolean isIn(int i, int j) {
		return i >= 0 && i < M && j >= 0 && j < N;
	}
}
