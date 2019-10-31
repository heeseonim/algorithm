import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main_백준_17135_캐슬디펜스 {
	public static int N, M, D;
	public static Vector<Point> v; // 사정거리 내 적군 저장
	public static HashSet<String> hs; // 맞힌 적군 저장
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] totArcher;
	public static int[] selArcher;
	public static int count, max;
	public static int enemy;

	public static class Point implements Comparable<Point> {
		int r;
		int c;
		int dist;

		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			if (this.dist == o.dist) {
				return this.c - o.c;
			}
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 사정거리

		map = new int[N][M];
		copyMap = new int[N][M];

		enemy = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) enemy++;
			}
		}

		totArcher = new int[M];
		for (int i = 0; i < M; i++) {
			totArcher[i] = i;
		}
		selArcher = new int[3];
		max = Integer.MIN_VALUE;
		comb(M, 3);
//		if (max > enemy) max = enemy;
		System.out.println(max);
	}

	public static void comb(int n, int r) {
		if (r == 0) {
			count = 0;
			for (int i = 0; i < map.length; i++) {
				copyMap[i] = map[i].clone();
			}
			for (int i = 0; i < N; i++) {
				shoot();
				move();
			}
			max = Math.max(count, max);
		} else if (n < r) {
			return;
		} else {
			selArcher[r - 1] = totArcher[n - 1];
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}
	}

	public static void shoot() {
		hs = new HashSet<>();
		
		for (int i = 0; i < selArcher.length; i++) {
			v = new Vector<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (copyMap[r][c] == 1) {
						int d = Math.abs(r - N) + Math.abs(c - selArcher[i]);
						if (d <= D) { // 거리 이하
							v.add(new Point(r, c, d));
						}
					}
				}
			}

			if (v.size() > 0) {
				Collections.sort(v); // 가능한 적군 정렬
				hs.add(v.get(0).r + " " + v.get(0).c); // 제일 가까운 적군 저장
			}
		}

		if (hs.size() > 0) {
			// 제거한 적군 수
			count += hs.size(); // 제거한 적군 수 누적 (turn 마다 map이 바뀌니까 누적)
			for (String p : hs) {
				StringTokenizer st = new StringTokenizer(p);
				copyMap[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 0; // 적군 제거
			}
		}
	}

	public static void move() {
		for (int i = N - 2; i >= 0; i--) { // 밑에서부터 시작
			for (int j = 0; j < M; j++) {
				copyMap[i + 1][j] = copyMap[i][j]; // 아래로 행 이동
			}
		}

		for (int i = 0; i < M; i++) { // 맨 윗 줄은 0으로 만듦
			copyMap[0][i] = 0;
		}
	}

}
