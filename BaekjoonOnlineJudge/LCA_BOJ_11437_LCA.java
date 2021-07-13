import java.io.*;
import java.util.*;

public class BOJ_11437_LCA {
	static int[] parent, rank;
	static List<Integer>[] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		rank = new int[N + 1];

		array = new LinkedList[N+1];

		for (int i = 1; i <= N; i++) {
			array[i] = new LinkedList<Integer>();
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			array[x].add(y);
			array[y].add(x);
		}

		dfs(1, 0, -1); // cur, rank, parent

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int rx = rank[x];
			int ry = rank[y];

			while (rx > ry) {
				x = parent[x];
				rx--;
			}

			while (rx < ry) {
				y = parent[y];
				ry--;
			}

			while (x != y) {
				x = parent[x];
				y = parent[y];
			}

			System.out.println(x);
		}
	}

	static void dfs(int cur, int r, int p) {
		parent[cur] = p;
		rank[cur] = r;

		for (int next : array[cur])
			if (next != p)
				dfs(next, r + 1, cur);
	}
}
