package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_2097_지하철 {
	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[] visited;
	public static int min;
	public static String list;

	public static void main(String[] args) throws Exception {
		// 정올 2097 지하철
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE; // 최소시간
		list = "";

		for (int i = 1; i < N; i++) {
			dfs(0, i, map[0][i], "1 " + (i + 1) + " ");
		}

		StringBuilder sb = new StringBuilder();
		sb.append(min).append('\n').append(list);
		System.out.println(sb);
	}

	public static void dfs(int start, int end, int time, String path) {
		if (time > min)
			return;

		if (end == M - 1) { // 목적지에 왔을 때 시간 체크
			if (min > time) {
				min = time;
				list = path;
			}
			return;
		}

		if (path.length() >= N * 2)
			return;

		for (int i = 1; i < N; i++) {
			if (map[end][i] != 0 && !visited[i]) {
				visited[start] = true;
				dfs(end, i, time + map[end][i], path + (i + 1) + " ");
				visited[start] = false;
			}
		}

	}
}
