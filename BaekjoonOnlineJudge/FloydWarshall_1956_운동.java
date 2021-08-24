import java.io.*;
import java.util.StringTokenizer;

public class FloydWarshall_1956_운동 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		final int M = Integer.MAX_VALUE;
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] distance = new int[V][V];

		// 최댓값 세팅
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (i == j)
					continue;
				distance[i][j] = M;
			}
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			distance[s][e] = val;
		}

		// floyd warshall
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < V; j++) {
					if (k == j || i == j)
						continue;
					if (distance[i][k] == M || distance[k][j] == M)
						continue;
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}

		// 최소 사이클 찾기
		int answer = M;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (i == j)
					continue;
				if (distance[i][j] == M || distance[j][i] == M)
					continue;
				answer = Math.min(answer, distance[i][j] + distance[j][i]);
			}
		}
		
		System.out.println(answer == M ? -1 : answer);
	}
}
