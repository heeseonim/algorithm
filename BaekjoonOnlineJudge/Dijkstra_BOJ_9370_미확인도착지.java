import java.io.*;
import java.util.*;

public class Dijkstra_BOJ_9370_미확인도착지 {
	public static List<Node>[] list;
	public static boolean[] destination;
	public static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken()); // 교차로
			int m = Integer.parseInt(st.nextToken()); // 도로
			int t = Integer.parseInt(st.nextToken()); // 목적지 후보

			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()); // 출발지
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				if ((start == g && end == h) || (start == h && end == g)) {
					list[start].add(new Node(end, val * 2 - 1)); // 지나야하는 간선은 홀수로 만들어주기
					list[end].add(new Node(start, val * 2 - 1));
				} else {
					list[start].add(new Node(end, val * 2));
					list[end].add(new Node(start, val * 2));
				}
			}

			destination = new boolean[n + 1];
			for (int i = 0; i < t; i++) {
				int num = Integer.parseInt(br.readLine());
				destination[num] = true;
			}

			dijkstra(s);
		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];

		Arrays.fill(dist, 98765432); // 홀수이면 정답이 되므로 ... 짝수로 수정
		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visited[cur.end])
				continue;
			visited[cur.end] = true;

			for (Node next : list[cur.end]) {
				if (visited[next.end])
					continue;
				int value = dist[cur.end] + next.val;
				if (dist[next.end] > value) {
					dist[next.end] = value;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < destination.length; i++) {
			if (destination[i]) { // 목적지에 해당하는 곳이면 값이 홀수인 지 검사
				if (dist[i] % 2 == 1) {
					sb.append(i).append(' ');
				}
			}
		}
		System.out.println(sb);
	}

	public static class Node implements Comparable<Node> {
		int end, val;

		public Node(int end, int val) {
			this.end = end;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return val - o.val;
		}
	}
}
