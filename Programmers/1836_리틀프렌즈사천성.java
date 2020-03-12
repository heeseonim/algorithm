package practice;

import java.util.HashMap;
import java.util.Map;

public class Solution_프로그래머스_1836_리틀프렌즈사천성 {
	public static void main(String[] args) {
		String[] brr = { "AB", "BA" };
		System.out.println(solution(2, 2, brr));
	}

	public static int M, N;
	public static Map<Character, Point> list;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static char[][] map;

	public static String solution(int m, int n, String[] board) {
		M = m;
		N = n;
		map = new char[M][N];
		list = new HashMap<>();
		boolean[] visited = new boolean[26];
		for (int i = 0; i < M; i++) {
			map[i] = board[i].toCharArray();
		}

		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				if (map[i][j] < 65 || visited[map[i][j] - 65])
					continue;
				list.put(map[i][j], new Point(i, j));
				visited[map[i][j] - 65] = true;
			}
		
		String answer = "";
		boolean flag = true;
		while (flag) {
			flag = false;
			for (char i = 65; i <= 90; i++) { // 'A'~'Z'
				if (!visited[i - 65])
					continue;
				flag = check(i, list.get(i));
				if (flag) {
					answer += i;
					visited[i-65] = false;
					break;
				} else {
					Point p = list.get(i);
					map[p.x][p.y] = i;
				}
			}
		}
		
		for(boolean b : visited)
			if (b) return "IMPOSSIBLE";

		return answer;

	}

	public static boolean check(char cur, Point p) {
		map[p.x][p.y] = '.';
		
		for (int i = 0; i < 4; i++) {
			int nx = p.x + dir[i][0];
			int ny = p.y + dir[i][1];

			while (isIn(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == cur)) {
				if (map[nx][ny] == cur) {
					map[nx][ny] = '.';
					return true;
				}

				int ldir = 2;
				if (i == 2 || i == 3)
					ldir = 0;
				int lx = nx + dir[ldir][0];
				int ly = ny + dir[ldir][1];
				while (isIn(lx, ly) && (map[lx][ly] == '.' || map[lx][ly] == cur)) {
					if (map[lx][ly] == cur) {
						map[lx][ly] = '.';
						return true;
					}
					lx += dir[ldir][0];
					ly += dir[ldir][1];
				}

				int rdir = 3;
				if (i==2 || i==3)
					rdir = 1;
				int rx = nx + dir[rdir][0];
				int ry = ny + dir[rdir][1];
				while (isIn(rx, ry) && (map[rx][ry] == '.' || map[rx][ry] == cur)) {
					if (map[rx][ry] == cur) {
						map[rx][ry] = '.';
						return true;
					}
					rx += dir[rdir][0];
					ry += dir[rdir][1];
				}
				
				// 같은 방향으로 이동
				nx += dir[i][0];
				ny += dir[i][1];
			} // while
		}

		return false;
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}
