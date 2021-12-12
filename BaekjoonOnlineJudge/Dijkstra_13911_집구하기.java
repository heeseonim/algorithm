import java.io.*;
import java.util.*;

// 1. 가상노드로 맥도날드, 스타벅스와의 거리 계산
// 2. sum 배열에 합한 값 넣어주기
// 3. 
public class Main {
	public static List<Point>[] m_adjList; // 맥세권 연결관계 리스트
	public static long[] m_dist; // 맥세권 거리값 배열
	public static List<Point>[] s_adjList; // 스세권 연결관계 리스트
	public static long[] s_dist; // 스세권 거리값 배열
	public static long[] sum; // 총 거리의 합
	public static int V, E; // 정점, 도로
	public static PriorityQueue<Point> m_pq; // 맥세권 pq
	public static PriorityQueue<Point> s_pq; // 스세권 pq

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		m_adjList = new ArrayList[V + 1];
		s_adjList = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			m_adjList[i] = new ArrayList<Main.Point>();
			s_adjList[i] = new ArrayList<Main.Point>();
		}
		m_dist = new long[V + 1];
		s_dist = new long[V + 1];
		Arrays.fill(m_dist, Long.MAX_VALUE); // 다익스트라를 위해 최댓값으로 채워주기
		Arrays.fill(s_dist, Long.MAX_VALUE);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			m_adjList[u].add(new Point(v, w)); // 양방향 연결
			m_adjList[v].add(new Point(u, w));
			s_adjList[u].add(new Point(v, w));
			s_adjList[v].add(new Point(u, w));
		}

		m_pq = new PriorityQueue<>();
		s_pq = new PriorityQueue<>();
		boolean[] store = new boolean[V+1]; // 가게 위치 구분 위함

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken()); // 맥도날드 수
		long x = Long.parseLong(st.nextToken()); // 맥세권 조건
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			m_pq.add(new Point(num, 0)); // 가상노드부터 맥도날드까지 거리 0
			m_dist[num] = 0;
			store[num] = true; // 가게 표시
		}
		st = new StringTokenizer(br.readLine(), " ");
		int S = Integer.parseInt(st.nextToken()); // 스타벅스 수
		long y = Long.parseLong(st.nextToken()); // 스세권 조건
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < S; i++) {
			int num = Integer.parseInt(st.nextToken());
			s_pq.add(new Point(num, 0)); // 가상노드부터 스타벅스까지 거리 0
			s_dist[num] = 0;
			store[num] = true; // 가게 표시
		}
		
		dijkstra('m'); // 각 다익스트라 진행
		dijkstra('s');
		
		sum = new long[V+1];
		Arrays.fill(sum, Long.MAX_VALUE); // 비교할 때 최솟값으로 잡지 않도록 최댓값으로 채워줌
		long min = Long.MAX_VALUE; // 정답에 쓰일 최솟값 변수
		for (int i = 1; i <= V; i++) {
			if (store[i] || m_dist[i] > x || s_dist[i] > y) // 집이 아니라면, 맥스세권이 아니라면
				continue;
			sum[i] = m_dist[i] + s_dist[i]; // 거리의 합
			min = Math.min(sum[i], min); // 최소거리
		}
		
		if (min == Long.MAX_VALUE) {
			System.out.println(-1); // 원하는 집이 존재하지 않으면
		} else {
			System.out.println(min);
		}
	}

	public static void dijkstra(char gbn) { // 다익스트라
		boolean[] visited = new boolean[V + 1];
		if (gbn == 'm') { // 맥도날드
			m_pq.add(new Point(0, 0)); // 가상노드 0
			m_dist[0] = 0;
			while (!m_pq.isEmpty()) {
				Point cur = m_pq.poll();

				if (visited[cur.num]) // 방문처리
					continue;
				visited[cur.num] = true;

				for (Point next : m_adjList[cur.num]) {
					if (m_dist[next.num] > m_dist[cur.num] + next.value) { // 최솟값으로
						m_dist[next.num] = m_dist[cur.num] + next.value;
						m_pq.add(new Point(next.num, m_dist[next.num]));
					}
				}
			}
		} else {
			s_pq.add(new Point(0, 0));
			s_dist[0] = 0;
			while (!s_pq.isEmpty()) {
				Point cur = s_pq.poll();

				if (visited[cur.num])
					continue;
				visited[cur.num] = true;

				for (Point next : s_adjList[cur.num]) {
					if (s_dist[next.num] > s_dist[cur.num] + next.value) {
						s_dist[next.num] = s_dist[cur.num] + next.value;
						s_pq.add(new Point(next.num, s_dist[next.num]));
					}
				}
			}
		}
	}

	public static class Point implements Comparable<Point> {
		int num;
		long value;

		public Point(int num, long value) {
			this.num = num;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) { // Long 대소비교 주의
			return Long.compare(this.value, o.value);
		}
	}
}
