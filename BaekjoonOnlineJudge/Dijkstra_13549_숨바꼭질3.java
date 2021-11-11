import java.io.*;
import java.util.*;

// Dijkstra
// 세 경우 모두 진행해보기
public class Main {
	public static int N, K;
	public static final int max = 100001; // 범위 잘 확인하기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(N));
	}

	public static int dijkstra(int start) {
		Deque<Point> q = new LinkedList<>();
		boolean[] visited = new boolean[max];
		int[] dist = new int[max];
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Point(start, 0));
		dist[start] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int position = cur.num;
			
			if (position == K) // 목적지 도달 시 종료
				return dist[cur.num];
			
			if (visited[position]) // * 방문처리
				continue;
			visited[position] = true;
			
			int left = position - 1;
			int right = position + 1;
			int jump = position * 2;

			if (jump < max && dist[jump] > cur.time) { // 순간이동
				dist[jump] = cur.time;
				// 시간이 제일 적게 드니까 앞에 추가
				q.addFirst(new Point(jump, cur.time));
			}
			
			if (left >= 0 && dist[left] > cur.time + 1) {
				dist[left] = cur.time + 1;
				q.add(new Point(left, cur.time + 1));
			}

			// K 보다 현재위치가 작을 때만 오른쪽으로 이동하기
			if (position < K && right < max && dist[right] > cur.time +1) {
				dist[right] = cur.time + 1;
				q.add(new Point(right, cur.time + 1));
			}
		}

		return 0;
	}

	public static class Point {
		int num, time;

		public Point(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

}
