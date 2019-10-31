import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페 {
	public static int[][] map;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int w = 1; w < N; w++) { // 폭
						for (int h = 1; h < N; h++) { // 높이
							if (j + w >= N || i + w + h >= N || j - h < 0)
								continue;
							int result = calc(i, j, w, h);
							if (answer < result)
								answer = result;
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	public static int calc(int r, int c, int w, int h) {
		boolean[] dessert = new boolean[101];

		for (int i = 0; i <= w; i++) { // 오른쪽 아래 대각선(w) 탐색, 꼭짓점 포함
			if (dessert[map[r + i][c + i]])
				return -1; // 방문한 카페라면 -1 리턴
			dessert[map[r + i][c + i]] = true;
			if (dessert[map[r + h + i][c - h + i]])
				return -1;
			dessert[map[r + h + i][c - h + i]] = true;
		}

		for (int i = 1; i < h; i++) { // 왼쪽 아래 대각선(h) 탐색, 꼭짓점 포함하지 않음
			if (dessert[map[r + i][c - i]])
				return -1;
			dessert[map[r + i][c - i]] = true;
			if (dessert[map[r + w + i][c + w - i]])
				return -1;
			dessert[map[r + w + i][c + w - i]] = true;
		}

		// 해당 사각형이 가능하다면
		return (w + h) * 2; // 둘레 길이 반환
	}
}
