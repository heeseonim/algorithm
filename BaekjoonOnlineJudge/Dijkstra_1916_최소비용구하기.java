import java.io.*;
import java.util.*;

public class Dijkstra_1916_최소비용구하기 {
	public static int N, M, start, end;
	public static List<Node>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, val)); // 양방향으로 등록하지 않게 주의
		}

		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();
	}

	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] d = new int[N + 1];
		Arrays.fill(d, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1]; // 꼭 챙겨주기

		d[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visited[cur.e])
				continue;
			visited[cur.e] = true;
			

			for (Node next : list[cur.e]) {
				int value = d[cur.e] + next.val;
				if (d[next.e] > value) {
					d[next.e] = value;
					pq.add(new Node(next.e, d[next.e]));
				}
			}
		}

		System.out.println(d[end]);
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
