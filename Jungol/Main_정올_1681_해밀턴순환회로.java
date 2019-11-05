import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_1681_해밀턴순환회로 {
	public static boolean[] visited;
	public static int N;
	public static int[][] map;
	public static int answer;

	// 정올 1681 해밀턴 순환회로
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = Integer.MAX_VALUE;
		for (int i = 1; i < N; i++) {
			visited = new boolean[N];
			if (map[0][i] != 0) {
				visited[i] = true;
				dfs(0, i, map[0][i], 0);
			}
		}

		System.out.println(answer);

	}

	public static void dfs(int r, int c, int sum, int count) {

		if (count == N - 2) {
			if (map[c][0] == 0)
				return;
			sum += map[c][0];
			if (answer > sum)
				answer = sum;
		}

		for (int i = 1; i < N; i++) {
			if (map[c][i] != 0 && !visited[i]) {
				visited[i] = true;
				dfs(c, i, sum + map[c][i], count + 1);
				visited[i] = false;
			}
		}

	}
}
