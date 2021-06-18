import java.io.*;
import java.util.*;

public class M5_03_신규도로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static List<Node> adj[];
	static int N, M;
	static int[] dist1;
	static int[] distN;
	static final int INF = (int) 21e8;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 노드
			M = Integer.parseInt(st.nextToken()); // 간선

			adj = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				adj[from].add(new Node(to, weight));
				adj[to].add(new Node(from, weight));
			}

			dist1 = new int[N + 1];
			Arrays.fill(dist1, INF);
			distN = new int[N + 1];
			Arrays.fill(distN, INF);

			// 다익스트라 두번 돌리기
			dijkstra(1, dist1);
			dijkstra(N, distN);

			// 2 ~ N-1 배열 복사
			int[] arr = Arrays.copyOfRange(distN, 2, N);
			Arrays.sort(arr);

			int cnt = 0;
			for (int i = 2; i <= N - 1; i++) {
				int a = dist1[i];
				int ori = dist1[N]; // 원래 비용 (1 -> N)

				int ret = binarySearch(arr, ori - a - 2); // arr[x] <= ori - a - 2 의 x 의 최댓값 찾기
				cnt += ret;
			}

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}

	public static int binarySearch(int[] arr, int target) {
		// arr[x] <= target 만족하는 x 의 최댓값을 찾게 되면
		// x 이하 모든 원소들이 조건 만족
		int s = 0;
		int e = arr.length;
		int ans = -1;

		while (s <= e) {
			int mid = (s + e) / 2;
			if (arr[mid] <= target) {
				ans = mid;
				s = mid + 1;
			} else
				e = mid - 1;
		}

		if (ans == -1)
			return 0;
		else
			return ans + 1;
	}

	public static void dijkstra(int start, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.num] < INF)
				continue; // 이미 결정되어 있는 경우
			dist[now.num] = now.cost;

			for (Node next : adj[now.num]) {
				pq.add(new Node(next.num, next.cost + now.cost));
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
