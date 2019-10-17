import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_14889_스타트와링크 {
	public static int N;
	public static int halfN;
	public static int[][] map;
	public static int min, cnt;
	public static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		halfN = N / 2;
		visited = new boolean[N];

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;
		cnt = 0;
		dfs(0);
		System.out.println(min);
	}

	public static void dfs(int v) {
		visited[v] = true;
		cnt++;

		if (cnt == halfN) { // 절반이 선택되었을 때
			calc();
			if (min == 0)
				return;
		}

		for (int i = v + 1; i < N; i++) {
			if (!visited[i])
				dfs(i);
		}

		visited[v] = false;
		cnt--;
	}

	public static void calc() {
		int[] start = new int[halfN];
		int[] link = new int[halfN];
		int sSum = 0, lSum = 0;
		int sIndex = 0, lIndex = 0;

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				start[sIndex++] = i;
			} else {
				link[lIndex++] = i;
			}
		}

		for (int i = 0; i < halfN; i++) {
			for (int j = i + 1; j < halfN; j++) {
				sSum += map[start[i]][start[j]] + map[start[j]][start[i]];
			}
		}

		for (int i = 0; i < halfN; i++) {
			for (int j = i + 1; j < halfN; j++) {
				lSum += map[link[i]][link[j]] + map[link[j]][link[i]];
			}
		}

		int result = sSum - lSum;
		if (result < 0)
			result = -result;
		if (min > result)
			min = result;
	}
}
