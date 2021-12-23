import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[][] arr;
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF); // 최댓값 세팅
			arr[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[u][v] = arr[v][u] = 1;
		}

		floydWarshall(); // 각 도로의 길이를 최단경로로 정리

		int minIndex = 100;
		int minValue = INF;

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += arr[i][j]; // 케빈 베이컨 계산
			}
			
			if (minValue >= sum) {
				if (minValue == sum) { // 같다면 더 작은 유저
					minIndex = Math.min(i, minIndex);
				} else
					minIndex = i;
				minValue = sum;
			}
		}

		sb.append(minIndex).append('\n');

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					if (j == k || i == j || arr[i][k] == INF || arr[k][j] == INF)
						continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}

	public static class Point {
		int u, v;
		long w;

		public Point(int u, int v, long w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

}
