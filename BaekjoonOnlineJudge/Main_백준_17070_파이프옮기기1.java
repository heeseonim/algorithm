import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070 {
	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
	public static int N, answer;
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		dfs(0, 1, 1);
		System.out.println(answer);
	}
	
	public static void dfs(int r, int c, int cur3) {
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}
		
		for (int i = 0; i < dir.length; i++) {
			if ((cur3 == 1 && i == 1) || (cur3 == 2 && i == 0))
				continue;
			int nx = r + dir[i][0];
			int ny = c + dir[i][1];

			if (!isIn(nx, ny) || map[nx][ny] != 0)
				continue;

			// 대각선 방향으로 갈 경우 꼭짓점의 왼,위 검사
			if (i == 2) {
				if (nx - 1 < 0 || map[nx - 1][ny] != 0 || ny - 1 < 0 || map[nx][ny - 1] != 0)
					continue;
			}

			dfs(nx, ny, i+1);
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
