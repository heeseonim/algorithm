import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static List<Point>[] adjList;
	public static final long INF = Long.MAX_VALUE;
	public static long[] fox;
	public static long[][] wolf;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		fox = new long[N + 1];
		wolf = new long[N + 1][2]; // 같은 정점에 짝수번째, 홀수번째 다르게 방문할 수 있다.
		Arrays.fill(fox, INF);
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			Arrays.fill(wolf[i], INF);
		}
		
		fox[1] = 0;
		wolf[1][0] = 0; // 출발 - 홀수번째

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adjList[a].add(new Point(b, d * 2)); // 양방향
			adjList[b].add(new Point(a, d * 2)); // 2배 해주어야 /2 했을 때 에러나지 않음
		}

		dijkstra(true, 1); // 달빛여우와 달빛늑대는 1번 그루터기에 살고 있다.
		dijkstra(false, 1);

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			if (fox[i] < Math.min(wolf[i][0], wolf[i][1]))
				sum++;
		}

		bw.append(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dijkstra(boolean gbn, int start) { // 다익스트라
		PriorityQueue<Point> pq = new PriorityQueue<>();

		if (gbn) { // 늑대
			boolean[][] visited = new boolean[N + 1][2];
			pq.add(new Point(start, 0, true)); // 홀수 true - 두배의 속도

			while (!pq.isEmpty()) {
				Point cur = pq.poll();
				int temp = cur.dir ? 0 : 1;

				if (visited[cur.num][temp])
					continue;
				visited[cur.num][temp] = true;

				for (Point next : adjList[cur.num]) {
					long value = 0l;
					
					if (cur.dir) { // 홀수번째 true - 두배의 속도
						value = cur.value + (next.value / 2); // cur.value : 갖고있는 현재 값
						temp = 1; // 짝수번째로 토스
					} else { // 짝수번째 false - 절반의 속도
						value = cur.value + (next.value * 2);
						temp = 0; // 홀수번째로 토스
					}

					if (wolf[next.num][temp] > value) {
						wolf[next.num][temp] = value;
						pq.add(new Point(next.num, wolf[next.num][temp], !cur.dir));
					}
				}
			}
		} else { // 여우
			boolean[] visited = new boolean[N + 1];
			pq.add(new Point(start, 0));

			while (!pq.isEmpty()) {
				Point cur = pq.poll();

				if (visited[cur.num])
					continue;
				visited[cur.num] = true;

				for (Point next : adjList[cur.num]) {
					if (fox[next.num] > fox[cur.num] + next.value) {
						fox[next.num] = fox[cur.num] + next.value;
						pq.add(new Point(next.num, fox[next.num]));
					}
				}
			}
		}
	}

	public static class Point implements Comparable<Point> {
		int num;
		long value;
		boolean dir;

		public Point(int num, long value) {
			this.num = num;
			this.value = value;
		}

		public Point(int num, long value, boolean dir) {
			this.num = num;
			this.value = value;
			this.dir = dir;
		}

		@Override
		public int compareTo(Point o) {
			return Long.compare(this.value, o.value);
		}
	}
}
