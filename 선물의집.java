import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1230_선물의집 {
	public static boolean[][] visited;
	public static int[][] map;
	public static int gift;
	public static int max;
	public static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		gift = 0;
		max = Integer.MIN_VALUE;
		dfs(0, 0);
		System.out.println(max);
	}

	public static void dfs(int r, int c) {
		if (r == map.length-1 && c == map.length-1) { // 오른쪽 맨 아래까지 도달했다면 리턴
			System.out.println("gift의 수 : " + gift);
			max = Math.max(gift, max);
			return;
		}
		visited[r][c] = true;
		for (int i = 0; i < dir.length; i++) {
			int newR = r + dir[i][0];
			int newC = c + dir[i][1];
			if (isIn(newR, newC) && !visited[newR][newC] && map[newR][newC] != 1) {
				if (map[newR][newC] == 2) {
					gift++;
				}
				dfs(newR, newC);
			}
		}
		
		if (map[r][c] == 2) {
			gift--;
		}
		visited[r][c] = false;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map.length;
	}
}
