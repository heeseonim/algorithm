import java.io.*;
import java.util.*;

public class Dijkstra_10282_해킹 {
	public static int n, d, c;
	public static List<Node>[] adjList;
	public static final int INF = 987654321;
	public static int cnt, time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<Node>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				// 영향을 받는 방향이 반대이므로 반대로 삽입!
				int e = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				adjList[s].add(new Node(e, val));
			}

			cnt = time = 0;
			dijkstra(c);
			sb.append(cnt).append(' ').append(time).append('\n');
		}
		System.out.println(sb);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[n + 1];
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

		// 값이 정리되고 난 후 계산해주기
		for (int i = 1; i <= n; i++) {
			if (dist[i] < 0 || dist[i] >= INF) // 값의 범위를 넘었을 수도 있으므로...
				continue;
			cnt++;
			time = Math.max(time, dist[i]); // 총 시간을 구해야 하므로 최댓값
		}
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
