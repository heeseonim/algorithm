import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Solution_1494_사랑의카운슬러 {
	public static Point[] map;
	public static int N;
	public static long min;
	public static boolean[] visited;
	public static int cnt;

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new Point[N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[i] = new Point(x, y);
			}

			cnt = 0;
			min = Long.MAX_VALUE;
			dfs(0);
			System.out.println("#" + tc + " " + min);
		}

	} // end of main

	public static void calc() {
		Vector<Point> selected = new Vector<>();
		Vector<Point> unselected = new Vector<>();

		// 선택된 벡터와 선택되지 않은 벡터로 구분
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				selected.add(map[i]);
			} else {
				unselected.add(map[i]);
			}
		}

		long x = 0;
		long y = 0;

		// 선택된 벡터와 선택되지 않은 벡터의 합을 구하기
		for (int i = 0; i < N / 2; i++) {
			x += (selected.get(i).x - unselected.get(i).x);
			y += (selected.get(i).y - unselected.get(i).y);
		}

		// 벡터의 합의 크기를 구하기
		long result = x * x + y * y;
		// 벡터의 합의 크기 중 최소값 저장하기
		min = Math.min(min, result);
	}

	public static void dfs(int v) {
		visited[v] = true;
		cnt++;

		// 절반이 선택되었다면
		if (cnt == N / 2) {
			calc();
		}

		// 다음 정점부터 선택 처리
		for (int i = v + 1; i < N; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}

		// 백트래킹
		visited[v] = false;
		cnt--;
	}

} // end of class
