import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1987_알파벳 {
	public static int R, C;
	public static char[][] map;
	public static boolean[] visitAlpha;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int count, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visitAlpha = new boolean[26];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		count = 1;
		answer = 1;
		dfs(0, 0);
		System.out.println(answer);
	}

	public static void dfs(int i, int j) {
		visitAlpha[map[i][j] - 65] = true;
		for (int k = 0; k < 4; k++) {
			int newI = i + dir[k][0];
			int newJ = j + dir[k][1];
			if (isIn(newI, newJ) && !visitAlpha[map[newI][newJ] - 65]) {
				answer = Math.max(answer, ++count);
				dfs(newI, newJ);
			}
		}
		count--;
		visitAlpha[map[i][j] - 65] = false;
	}

	public static boolean isIn(int i, int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}
}
