import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4613 {
	// swea 4613 러시아 국기 같은 깃발
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] color = new int[N][3];
			
			// 행마다 색의 수를 저장
			for (int i = 0; i < N; i++) {
				String str = br.readLine().trim();
				for (int j = 0; j < M; j++) {
					char c = str.charAt(j);
					switch (c) {
					case 'W':
						color[i][0]++;
						break;
					case 'B':
						color[i][1]++;
						break;
					case 'R':
						color[i][2]++;
						break;
					}
				}
			}
			
			// 모든 경우 탐색
			int answer = Integer.MAX_VALUE;
			for (int B = 1; B < N - 1; B++) { // B 영역
				for (int R = 1; R < N - B; R++) { // R 영역
					int result = 0;
					for (int i = 0; i < B; i++) {
						result += (M - color[i][0]); // W 칠하기
					}
					for (int i = B; i < B + R; i++) {
						result += (M - color[i][1]); // B 칠하기
					}
					for (int i = B + R; i < N; i++) {
						result += (M - color[i][2]); // R 칠하기
					}
					if (answer > result) {
						answer = result;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
}
