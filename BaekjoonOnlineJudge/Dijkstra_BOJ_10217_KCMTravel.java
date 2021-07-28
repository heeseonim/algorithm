import java.io.*;
import java.util.*;

public class Dijkstra_BOJ_10217_KCMTravel {
	static int N, M;
	static List<Node>[] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				map[i] = new ArrayList<>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int pay = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				map[s].add(new Node(e, pay, time));
			}

			dijkstra(1); // 1번 도시에서 출발
		}
	}

	public static void dijkstra(int start) {
		int[][] D = new int[N + 1][M + 1]; // [노드][비용], 이동시간 저장
		for (int i = 1; i <= N; i++) {
			Arrays.fill(D[i], Integer.MAX_VALUE);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0, 0));
		D[start][0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.end == N) // LA 도착
				break;
			if (D[cur.end][cur.pay] < cur.time) // 저장된 시간보다 크면 넘어감
				continue;
			D[cur.end][cur.pay] = cur.time; // 시간 저장

			for (Node next : map[cur.end]) {
				int p = cur.pay + next.pay; // 현재 -> 넥스트 비용
				int t = cur.time + next.time; // 현재 -> 넥스트 시간

				if (p > M) // 비용 초과했다면 넘김
					continue;
				if (D[next.end][p] > t) {
					// 현재 노드에서 현재 비용부터 최대 비용까지의 시간을 갱신 (최소로...)
					// 현재 노드의 이동시간을 최소로 갱신
					for (int i = p; i <= M; i++) {
						if (D[next.end][i] > t)
							D[next.end][i] = t;
					}

					pq.add(new Node(next.end, p, t));
				}
			}
		}

		// 해당 노드들에 대해서 시간을 갱신하였으니까, 최솟값을 찾아줌
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= M; i++) {
			min = Math.min(min, D[N][i]); // LA 위치에서 비용별로 저장된 시간 중 최솟값 찾기
		}
		
		if (min == Integer.MAX_VALUE)
			System.out.println("Poor KCM");
		else
			System.out.println(min);
	}

	public static class Node implements Comparable<Node> {
		int end, pay, time;

		public Node(int end, int pay, int time) {
			this.end = end;
			this.pay = pay;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			if (this.time == o.time)
				return pay - o.pay;
			return time - o.time;
		}
	}

}
