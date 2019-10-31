import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4301_콩많이심기 {
	private static boolean[][] visited;
	private static int[][] map;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[M][N];
			visited = new boolean[M][N];
			
			visited[0][0] = true;
			for (int i = 0; i < 4; i++) {
				int newR = dir[i][0]*2;
				int newC = dir[i][1]*2;
				if (isIn(newR, newC)) {
					visited[newR][newC] = true;
				}
			}
			cnt = 1; // 콩 하나 심고 시작
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (choice(i, j)) { // 해당 자리에 심을 수 있다면
						cnt++;
					}
				}
			}
			System.out.println("#" + tc + " " + cnt);

		} // end of tc

	} // end of main

	// 해당 좌표에 콩을 심을 수 있는지 확인
	public static boolean choice(int r, int c) {
		if (!visited[r][c]) {
			visited[r][c] = true;
			// 길이 2인 자리 처리
			for (int i = 0; i < 4; i++) {
				int newR = r + dir[i][0]*2;
				int newC = c + dir[i][1]*2;
				if (isIn(newR, newC)) {
					visited[newR][newC] = true;
				}
			}
			return true;
		} else {
			return false;
		}		
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}

} // end of class
