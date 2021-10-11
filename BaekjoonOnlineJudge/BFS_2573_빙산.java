import java.io.*;
import java.util.*;

public class BFS_2573_빙산 {
	public static int N, M;
	public static int[][] map;
	public static int[][] nmap;
	public static Queue<Point> q;
	public static Queue<Point> nq;
	public static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		nmap = new int[N][M];
		
		q = new LinkedList<Main.Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					q.add(new Point(i, j, map[i][j]));
				}
			}
		}

		int year = 0;
		int mass = 0;
		while (true) {
			year++;
			cal();
			//print();
			q = nq;
			for (int i = 0; i < N; i++) {
				map[i] = nmap[i].clone(); // 새로운 맵으로 복사
			}
			// q 비었는지 검사
			if (q.isEmpty()) {
				System.out.println(0);
				break;
			}
			// 덩어리 검사
			mass = check();
			// 두 덩어리 이상이면
			if (mass >= 2) {
				System.out.println(year);
				break;
			}
		}
	}

	// 빙산 덩어리 개수 세기
	public static int check() {
		visited = new boolean[N][M];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					bfs(i, j);
					cnt++;
				}
			}
		}

		return cnt;
	}

	// 연결관계 체크
	public static void bfs(int r, int c) {
		Queue<Point> bq = new LinkedList<Main.Point>();
		bq.add(new Point(r, c));
		while (!bq.isEmpty()) {
			Point cur = bq.poll();
			for (int i = 0; i < dir.length; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];

				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;

				visited[nx][ny] = true;
				bq.add(new Point(nx, ny));
			}
		}
	}

	// 바다 수를 세어 감소해주는 함수
	public static void cal() {
		nq = new LinkedList<Main.Point>();
		for (int i = 0; i < N; i++) {
			nmap[i] = map[i].clone(); // 기존 맵 복사
		}
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int cnt = 0;
			for (int i = 0; i < dir.length; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if (map[nx][ny] == 0) { // 바다 수 카운트
					cnt++;
				}
			}
			
			// 바다의 수만큼 감소해주기
			cur.height -= cnt;
			nmap[cur.x][cur.y] -= cnt; // 새로운 맵에 표시
			
			if (cur.height < 0) { // 마이너스가 되지 않도록 처리
				cur.height = 0;
				nmap[cur.x][cur.y] = 0;
			}
			
			if (cur.height > 0)
				nq.add(cur); // 빙산이 살아있다면 새로운 큐에 추가
		}
	}

	public static class Point {
		int x, y, height;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
	
	public static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
