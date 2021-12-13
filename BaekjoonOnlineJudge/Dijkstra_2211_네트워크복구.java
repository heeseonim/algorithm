import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static List<Point>[] adjList;
	public static int[] p;
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adjList[A].add(new Point(B, C)); // 양방향
			adjList[B].add(new Point(A, C));
		}
		
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		dijkstra(1); // 슈퍼컴퓨터로부터 최소시간 계산
		StringBuilder sb = new StringBuilder();
		sb.append(N - 1).append('\n'); // 최소 회선 개수
		for (int i = 2; i <= N; i++) { // N-1 개
			sb.append(p[i]).append(' ').append(i).append('\n'); // 연결관계 표시
		}
		
		bw.append(sb);
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dijkstra(int start) { // 다익스트라
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		pq.add(new Point(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			int cur = pq.poll().num;

			if (visited[cur])
				continue;
			visited[cur] = true;

			for (Point next : adjList[cur]) {
				if (dist[next.num] > dist[cur] + next.value) {
					dist[next.num] = dist[cur] + next.value;
					pq.add(new Point(next.num, dist[next.num]));
					p[next.num] = cur; // 부모표시 - 나중에 회선 연결관계 표시
				}
			}
		}

	}

	public static class Point implements Comparable<Point> {
		int num, value;

		public Point(int num, int value) {
			this.num = num;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}
	}
}
