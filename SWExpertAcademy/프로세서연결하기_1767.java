import java.io.*;
import java.util.*;

public class 프로세서연결하기_1767 {
	public static int[][] map;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int N, maxCore, answer;
	public static LinkedList<Point> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			map = new int[N][N];
			list = new LinkedList<>();

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
						continue;
					if (map[i][j] == 1)
						list.add(new Point(i, j));
				}
			}

			maxCore = 0;
			answer = Integer.MAX_VALUE;

			dfs(0, 0, 0);

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb.toString());
		}
	}

	public static void dfs(int index, int coreCnt, int sum) {
		if (index == list.size()) { // 종료조건
			if (coreCnt > maxCore) { // 더 많을 경우 바로 answer 갱신
				maxCore = coreCnt;
				answer = sum;
			} else if (maxCore == coreCnt) // 같을 땐 비교
				answer = answer > sum ? sum : answer;
			
			return;
		}

		Point p = list.get(index);

		for (int i = 0; i < dir.length; i++) {
			int len = 0;
			int x = p.x;
			int y = p.y;

			while (true) {
				x += dir[i][0];
				y += dir[i][1];
				
				if (!isIn(x, y)) // 끝에 도달하면 멈춤
					break;

				if (map[x][y] == 1) { // 중간에 교차하거나 코어가 있다면
					len = 0;
					break;
				}

				len++;
			}

			if (len == 0) { // 인덱스만 증가
				dfs(index + 1, coreCnt, sum);
			} else {
				x = p.x;
				y = p.y;
				for (int j = 0; j < len; j++) {
					x += dir[i][0];
					y += dir[i][1];
					map[x][y] = 1;
				}

				dfs(index + 1, coreCnt + 1, sum + len);

				x = p.x;
				y = p.y;
				for (int j = 0; j < len; j++) {
					x += dir[i][0];
					y += dir[i][1];
					map[x][y] = 0;
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
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
