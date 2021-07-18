import java.io.*;
import java.util.*;

public class 기출_치킨배달 {
	public static Vector<Point> chicken;
	public static Vector<Point> house;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		house = new Vector<>();
		chicken = new Vector<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());

				if (cur == 1)
					house.add(new Point(i, j));
				else if (cur == 2)
					chicken.add(new Point(i, j));
			}
		}

		answer = Integer.MAX_VALUE;
		boolean[] visit = new boolean[chicken.size()];
		comb(visit, 0, chicken.size(), M);

		System.out.println(answer);

	}

	public static void comb(boolean[] visit, int start, int n, int r) {
		if (r == 0) {
			int total = 0;

			for (int i = 0; i < house.size(); i++) {
				Point p = house.get(i);
				int min = Integer.MAX_VALUE;

				for (int j = 0; j < visit.length; j++) {
					if (visit[j]) { // 선택된 치킨집의 좌표
						int dist = Math.abs(p.x - chicken.get(j).x) + Math.abs(p.y - chicken.get(j).y);
						min = min > dist ? dist : min;
					}
				}

				total += min;
			}

			answer = answer > total ? total : answer;

			return;
		}

		for (int i = start; i < n; i++) { // 치킨집 개수만큼 돌면서
			if (!visit[i]) {
				visit[i] = true;
				comb(visit, i + 1, n, r - 1);
				visit[i] = false;
			}
		}

	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
