import java.io.*;
import java.util.*;

public class Dijkstra_BOJ_1162_도로포장 {
	public static int N, M, K;
	public static List<Node>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long val = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, val, 0));
			list[e].add(new Node(s, val, 0));
		}

		dijkstra(1);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		long[][] dist = new long[N + 1][K + 1]; // [노드][포장수] = 시간
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
		}

		pq.add(new Node(start, 0, 0));
		dist[start][0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (dist[cur.end][cur.cnt] < cur.val) // 시간초과 나지 않도록 가지치기 *
				continue;

			for (Node next : list[cur.end]) {
				int count = cur.cnt + next.cnt; // 포장 수
				long value = cur.val + next.val; // 시간

				if (dist[next.end][count] > value) { // 도로포장 하지 않는 경우
					dist[next.end][count] = value;
					pq.add(new Node(next.end, value, count));
				}

				if (count < K && dist[next.end][count + 1] > cur.val) { // next 도로를 포장하는 경우
					dist[next.end][count + 1] = cur.val;
					pq.add(new Node(next.end, cur.val, count + 1));
				}
			}
		}

		long min = Long.MAX_VALUE;
		for (int i = 1; i <= K; i++) {
			min = Math.min(min, dist[N][i]);
		}
		System.out.println(min);
	}

	public static class Node implements Comparable<Node> {
		int end;
		long val;
		int cnt;

		public Node(int end, long val, int cnt) {
			this.end = end;
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(val, o.val);
		}
	}
}
