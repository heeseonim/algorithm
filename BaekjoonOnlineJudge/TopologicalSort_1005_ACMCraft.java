import java.io.*;
import java.util.*;

public class TopologicalSort_1005_ACMCraft {
	public static int N, K, W;
	public static List<Integer>[] adjList;
	public static long[] time;
	public static int[] indegree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			time = new long[N + 1];
			indegree = new int[N + 1];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				time[i] = Long.parseLong(st.nextToken());
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList[s].add(e);
				indegree[e]++; // 선행되어야 할 작업 수 증가
			}

			W = Integer.parseInt(br.readLine());
			TopologicalSort();
		}
	}

	public static void TopologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		long[] result = time.clone();
		for (int i = 1; i < indegree.length; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == W) {
				System.out.println(result[cur]);
				return;
			}

			for (int next : adjList[cur]) {
				// * result : 누적시간
				result[next] = Math.max(result[next], result[cur] + time[next]);

				indegree[next]--;
				if (indegree[next] == 0)
					q.add(next);
			}
		}
	}

}

