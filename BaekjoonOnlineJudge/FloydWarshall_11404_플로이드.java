import java.io.*;
import java.util.*;

public class FloydWarshall_11404_플로이드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] distance = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				distance[i][j] = Integer.MAX_VALUE;
			}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int time = Integer.parseInt(st.nextToken());
			distance[start][end] = Math.min(distance[start][end], time);
		}

		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < N; j++) {
					if (k == j || i == j)
						continue;
					if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
						continue;
					distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
				}
			}

		for (int[] arr : distance) {
			for (int item : arr)
				System.out.print((item == Integer.MAX_VALUE ? "0" : item) + " ");
			System.out.println();
		}
	}
}
