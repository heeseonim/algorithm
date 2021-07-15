import java.io.*;
import java.util.*;

public class Dijkstra_BOJ_1753_최단경로 {
	static final int INF = Integer.MAX_VALUE;
	static List<Node>[] list;
	static int[] dist;
	static int V;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken()); // 정점
		int E = Integer.parseInt(st.nextToken()); // 간선
		int K = Integer.parseInt(br.readLine()); // 시작 정점

		list = new ArrayList[V + 1];
		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, val));
		}

		dijkstra(K);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V + 1];
		
		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			int cur = pq.poll().num;
			
			if (check[cur])
				continue;
			check[cur] = true;
			
			for (Node next : list[cur]) {
				if (dist[next.num] > dist[cur] + next.weight) {
					dist[next.num] = dist[cur] + next.weight;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}
	}

	public static class Node implements Comparable<Node> {
		int num, weight;

		public Node(int node, int weight) {
			this.num = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}

}
