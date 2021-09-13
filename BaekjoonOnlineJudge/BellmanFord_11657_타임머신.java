import java.io.*;
import java.util.*;

public class BellmanFord_11657_타임머신 {
	public static int N, M;
	public static List<Node> adjList;
	public static final long INF = 987654321;
	public static long[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<Node>();

		// 1. 연결관계 표시
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long val = Long.parseLong(st.nextToken());
			adjList.add(new Node(s, e, val));
		}

		// 2. 벨만포드 알고리즘 검사
		if (BellmanFord())
			System.out.println("-1");
		// 3. 아니라면 값 출력
		else {
			for (int i = 2; i <= N; i++) {
				System.out.println(dist[i] == INF ? "-1" : dist[i]);
			}
		}
	}

	// 벨만포드 알고리즘
	public static boolean BellmanFord() {
		dist = new long[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		boolean updated = false;
		
		for (int i = 1; i <= N; i++) { // N-1 만큼 최소값 갱신
			updated = false;
			for(Node cur : adjList) {
				if (dist[cur.s] == INF)
					continue;
				if (dist[cur.e] > dist[cur.s] + cur.val) {
					dist[cur.e] = dist[cur.s] + cur.val;
					updated = true;
					if (i == N) { // 음수사이클 발생
						return true;
					}
				}
			}
			if (!updated) // 갱신되지 않았다면 멈춤
				break;
		}
		
		return false;
	}

	public static class Node {
		int s, e;
		long val;

		public Node(int s, int e, long val) {
			this.s = s;
			this.e = e;
			this.val = val;
		}
	}
}
