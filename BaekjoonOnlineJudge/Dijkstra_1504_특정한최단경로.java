import java.io.*;
import java.util.*;

public class Dijkstra_1504_특정한최단경로 {
	public static int N, E, v1, v2;
	public static List<Node>[] adjList;
	public static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Node(b, c));
			adjList[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		int result = calc();
		if (result < 0) // int 의 범위를 넘어서 -가 되어버림
			System.out.println("-1");
		else
			System.out.println(result);
			
	}

	public static int calc() {
		int v1v2 = 0, v2v1 = 0;

		// 1 -> v1 -> v2 -> N
		v1v2 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		// 1 -> v2 -> v1 -> N
		v2v1 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

		if (v1v2 >= INF && v2v1 >= INF)
			return -1;
		else
			return Math.min(v1v2, v2v1);
	}

	public static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1]; // 방문처리
		Arrays.fill(dist, INF);
		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visited[cur.e])
				continue;
			visited[cur.e] = true;

			for (Node next : adjList[cur.e]) {
				if (visited[next.e])
					continue;
				if (dist[next.e] > cur.val + next.val) {
					dist[next.e] = cur.val + next.val;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}

		return dist[end];

	}

	public static class Node implements Comparable<Node> {
		int e, val;

		public Node(int e, int val) {
			this.e = e;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return val - o.val;
		}
	}

}
