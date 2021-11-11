import java.io.*;
import java.util.*;

// Dijkstra
// 각 마을 [출발 마을 -> 모이는 마을 -> 출발 마을] 계산
public class Main {
	public static int N, M, X;
	public static List<Point>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			adjList[s].add(new Point(e, val)); // 단방향
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			int result = dijkstra(i);
			max = Math.max(max, result);
		}
		System.out.println(max);
	}

	// 다익스트라 두번 진행, s -> X -> s
	public static int dijkstra(int start) {
		int sum = 0;

		PriorityQueue<Point> pq = new PriorityQueue<>();
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Point(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			for (Point next : adjList[cur.num]) {
				if (dist[next.num] > cur.val + next.val) {
					dist[next.num] = cur.val + next.val;
					pq.add(new Point(next.num, dist[next.num]));
				}
			}
		}

		sum += dist[X - 1]; // 배열이 0~N

		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Point(X - 1, 0));
		dist[X - 1] = 0;
		
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			for (Point next : adjList[cur.num]) {
				if (dist[next.num] > cur.val + next.val) {
					dist[next.num] = cur.val + next.val;
					pq.add(new Point(next.num, dist[next.num]));
				}
			}
		}
		
		return sum += dist[start];
	}

	public static class Point implements Comparable<Point> {
		int num, val;

		public Point(int num, int val) {
			this.num = num;
			this.val = val;
		}

		@Override
		public int compareTo(Point o) {
			return this.val - o.val;
		}
	}
}
