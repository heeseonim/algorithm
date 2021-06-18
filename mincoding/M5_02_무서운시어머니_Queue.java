import java.io.*;
import java.util.*;

public class M5_02_무서운시어머니_Queue {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] map;
	static int N;
	static int sr, sc; // 시어머니 좌표
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] dist;
	static int INF = (int)21e8;
	static int ans = (int)-21e8;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		dijkstra(sr, sc);
		
		System.out.println(ans);
	}
	
	public static void dijkstra(int sr, int sc) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(sr, sc, map[sr][sc]));
		
		while(!pq.isEmpty()) { // 피로도 제일 큰 곳까지 가면 되니까, 끝까지 진행
			Node now = pq.poll();
			
			if (dist[now.r][now.c] < INF)
				continue;
			dist[now.r][now.c] = now.cost; // 최소비용 결정
			ans = now.cost;
			
			for (int i = 0; i < dir.length; i++) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				
				if (!isIn(nr, nc) || map[nr][nc] == -1)
					continue;
				
				pq.add(new Node(nr, nc, now.cost + map[nr][nc]));
			}
		}
	}


	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static class Node implements Comparable<Node>{
		int r, c, cost;

		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
