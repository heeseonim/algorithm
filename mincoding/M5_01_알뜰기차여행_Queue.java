import java.io.*;
import java.util.*;

public class M5_01_알뜰기차여행_Queue {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static List<Node> adj[];
	static int N, T;
	static int[] dist;
	static final int INF = (int) 21e8;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 노드
		T = Integer.parseInt(st.nextToken()); // 간선

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[from].add(new Node(to, weight));
		}

		dist = new int[N];
		Arrays.fill(dist, INF);
		dijkstra(0);
		
		if (dist[N - 1] == INF)
			System.out.println("impossible");
		else
			System.out.println(dist[N - 1]);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.num] < INF)
				continue; // 이미 결정되어 있는 경우
			dist[now.num] = now.cost;
			
			// ver 1
			for (int i = 0; i < adj[now.num].size(); i++) { // 다음 노드 넣기
				Node next = adj[now.num].get(i);
				pq.add(new Node(next.num, now.cost + next.cost));
			}
			
			// ver 2
			for(Node next : adj[now.num]) {
				if (now.cost + next.cost < dist[now.num]) {
					dist[next.num] = now.cost + next.cost;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}

	}

	public static class Node implements Comparable<Node> {
		int num, cost; // 노드번호, 비용

		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
