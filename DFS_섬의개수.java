import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4963 {
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
			{ 1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			map = new int[h][w];
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						dfs(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}

	public static void dfs(int r, int c) {
		visited[r][c] = true;
		for (int i = 0; i < dir.length; i++) {
			int newR = r + dir[i][0];
			int newC = c + dir[i][1];
			if(isIn(newR, newC) && !visited[newR][newC] && map[newR][newC] == 1) {
				map[newR][newC] = 0;
				dfs(newR, newC);
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
}
