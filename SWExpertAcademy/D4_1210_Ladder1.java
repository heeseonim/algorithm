import java.io.*;
import java.util.*;

public class D4_1210_Ladder1 {
	public static int[][] map;
	public static int answer;
	public static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 } }; // 우 좌 하

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		while (T-- > 0) {
			int tc = Integer.parseInt(br.readLine());
			map = new int[100][100];

			StringTokenizer st;
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int d = 2; // 시작은 아래방향
			answer = 0;
			for (int i = 0; i < 100; i++)
				if (map[0][i] == 1)
					dfs(0, i, i, d);

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb.toString());
		}

	}

	// 현재 위치, 시작점, 현재 방향
	// 0 : 우 1 : 좌 2 : 하
	public static void dfs(int r, int c, int start, int d) {

		if (map[r][c] == 2) { // 종료
			answer = start;
			return;
		}

		int nr = r + dir[d][0];
		int nc = c + dir[d][1];
		
		if (r == 0) {
			dfs(nr, nc, start, d);
			return;
		}

		// 현재 아래방향이면 좌우 검사
		if (d == 2) {
			// 좌 검사
			int lr = r + dir[1][0];
			int lc = c + dir[1][1];

			// 우 검사
			int rr = r + dir[0][0];
			int rc = c + dir[0][1];

			if (isIn(lr, lc) && map[lr][lc] > 0) {
				dfs(lr, lc, start, 1);
			} else if (isIn(rr, rc) && map[rr][rc] > 0) {
				dfs(rr, rc, start, 0);
			} else if (isIn(nr, nc) && map[nr][nc] > 0) {
				dfs(nr, nc, start, d);
			}
		}

		// 현재 좌우방향이면 계속 그방향 진행, 더이상 1없으면 아래방향으로 전환
		else {
			int dr = r + dir[2][0];
			int dc = c + dir[2][1];
			
			if (isIn(nr, nc) && map[nr][nc] > 0) {
				dfs(nr, nc, start, d);
			} else if (isIn(dr, dc) && map[dr][dc] > 0) {
				dfs(dr, dc, start, 2);
			}
		}

	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < 100 && c >= 0 && c < 100;
	}
}
