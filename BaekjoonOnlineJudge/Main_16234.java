import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234 {
	public static int N, L, R, count;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	public static boolean flag;

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
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		count = 0;
		flag = true;
		
		while (flag) {
			flag = false;
			
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (!visited[i][j]) {
						bfs(i, j);
					}
			
			if (flag) count++;
		}

		System.out.println(count); // 지금까지 생성된 연합의 수
	}

	public static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		List<Point> list = new ArrayList<>();

		queue.add(new Point(r, c));
		list.add(new Point(r, c));
		visited[r][c] = true;
		int sum = map[r][c];
		int num = 1;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < dir.length; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];

				if (!isIn(nx, ny) || visited[nx][ny])
					continue;

				int diff = Math.abs(map[nx][ny] - map[p.x][p.y]);
				if (L <= diff && diff <= R) {
					queue.add(new Point(nx, ny));
					list.add(new Point(nx, ny));
					visited[nx][ny] = true;
					sum += map[nx][ny];
					num++;
				}
			}
		}

		// 연합이 이루어졌다면
		if (num >= 2) {
			flag = true;
			int result = sum / num;
			for (Point a : list) { // 저장된 나라 돌면서 값 바꿔주기
				map[a.x][a.y] = result;
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
