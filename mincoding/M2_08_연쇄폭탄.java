import java.io.*;
import java.util.*;

public class M2_08_연쇄폭탄 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] arr;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static TreeMap<Integer, Point> tm;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		tm = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				arr[i][j] = cur;
				tm.put(cur, new Point(i, j));
			}
		}

		int cnt = 0;
		for (int i = 1; i <= N * N; i++) {
			if (tm.isEmpty()) // 모두 비었을 시 종료
				break;

			cnt++; // 초 계산
			if (tm.get(i) == null) // 없을 시 넘어가기
				continue;

			bomb(tm.get(i)); // 폭탄 터트리기
			tm.remove(i);
		}

		sb.append(cnt).append("초");
		System.out.println(sb);
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 연결된 폭탄 터트리기
	public static void bomb(Point p) {
		for (int i = 0; i < dir.length; i++) {
			int nr = p.r + dir[i][0];
			int nc = p.c + dir[i][1];
			
			if (!isIn(nr,nc))
				continue;
			int cur = arr[nr][nc];
			
			if (tm.get(cur) != null) {
				tm.remove(cur);
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
