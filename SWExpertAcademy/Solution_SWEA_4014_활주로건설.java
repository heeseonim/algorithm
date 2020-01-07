import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_4014_활주로건설 {
	public static int N;
	public static int X;
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				if (checkR(i)) {
					answer++;
				}
				if (checkC(i)) {
					answer++;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb);
		}

	}

	public static boolean checkR(int r) {
		int cur = map[r][0];
		int cnt = 1;
		int check = 0;

		for (int i = 1; i < N; i++) {
			if (cur == map[r][i])
				cnt++;
			else if (cur > map[r][i]) { // 작아질 때
				if (cur - map[r][i] > 1)
					return false;
				if (i + X - 1 >= N)
					return false;

				cur = map[r][i];
				for (int k = i + 1; k < i + X; k++)
					if (map[r][k] != cur)
						return false;
				cnt = 1;
				check = i + X; // 다음 좌표 저장
			} else if (cur < map[r][i]) { // 커질 때
				if (i - X < check)
					return false;
				if (map[r][i] - cur > 1)
					return false;
				if (cnt < X)
					return false;
				else {
					cur = map[r][i];
					cnt = 1;
				}
			}
		}

		return true;
	}

	public static boolean checkC(int c) {
		int cur = map[0][c];
		int cnt = 1;
		int check = 0;

		for (int i = 1; i < N; i++) {
			if (cur == map[i][c])
				cnt++;
			else if (cur > map[i][c]) { // 작아질 때
				if (cur - map[i][c] > 1)
					return false;
				if (i + X - 1 >= N)
					return false;
				cur = map[i][c];
				for (int k = i + 1; k < i + X; k++)
					if (map[k][c] != cur)
						return false;
				cnt = 1;
				check = i + X; // 다음 좌표 저장 (경사로 설치한 구역)
			} else if (cur < map[i][c]) { // 커질 때
				if (i - X < check) // 경사로 설치한 구역 내 좌표라면
					return false;
				if (map[i][c] - cur > 1)
					return false;
				if (cnt < X)
					return false;
				else {
					cur = map[i][c];
					cnt = 1;
				}
			}
		}

		return true;
	}
}
