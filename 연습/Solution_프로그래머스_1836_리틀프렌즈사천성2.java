package 연습;

import java.util.HashMap;
import java.util.Map;

public class Solution_프로그래머스_1836_리틀프렌즈사천성2 {
	public static void main(String[] args) {
		String[] arr = { "NRYN", "ARYA" };
		System.out.println(solution(2, 4, arr));
	}

	public static char[][] map;

	public static String solution(int m, int n, String[] board) {
		String answer = "";
		map = new char[m][n];
		boolean[] visited = new boolean[26];
		Map<Character, Point> alpha = new HashMap<>();

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				map[i][j] = board[i].charAt(j);
				if (map[i][j] >= 65 && !visited[map[i][j] - 65]) {
					alpha.put(map[i][j], new Point(i, j)); // 존재하는 알파벳의 위치 맵에 넣기
					visited[map[i][j] - 65] = true; // 어차피 중복처리 해주지만 그냥...
				}
			}

		boolean flag = true;
		while (flag) {
			flag = false;
			for (char i = 65; i <= 90; i++) {
				if (!visited[i - 65])
					continue;
				flag = check(alpha.get(i), m, n);
				if (flag) {
					visited[i - 65] = false;
					answer += i;
					break; // 알파벳 순으로 출력되어야 하므로 다시 시작하도록 함
				}
			}
			for(char[] a:map) {
				for(char b : a) {
					System.out.print(b);
				}
				System.out.println();
			}
		}

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] != '.' || map[i][j] != '*')
					return "IMPOSSIBLE";

		return answer;
	}

	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static boolean check(Point p, int m, int n) {
		int x = p.x;
		int y = p.y;
		char cur = map[x][y];
		System.out.println("cur" + cur);
		for(char[] a:map) {
			for(char b : a) {
				System.out.print(b);
			}
			System.out.println();
		}

		for (int i = 0; i < 4; i++) {
			int r = x + dir[i][0];
			int c = y + dir[i][1];

			// 양옆으로 검사 쭉 가보고 존재하면 return true, 없으면 한칸 전진
			while (isIn(r, c) && (map[r][c] == '.' || map[r][c] == cur)) {
				if (map[r][c] == cur) {
					map[x][y] = map[r][c] = '.'; // 사라진 자리는 .으로 채우기
					System.out.println(map[x][y]);
					System.out.println(map[r][c]);
					return true;
				}

				// 왼쪽 검사
				int ldir = (i + 2) % 4;
				int lr = r + dir[ldir][0];
				int lc = c + dir[ldir][1];
				while (isIn(lr, lc) && (map[lr][lc] == '.' || map[lr][lc] == cur)) {
					if (map[lr][lc] == cur) {
						map[lr][lc] = map[x][y] = '.';
						System.out.println(map[x][y]);
						System.out.println(map[lr][lc]);
						return true;
					}
					lr = lr + dir[ldir][0];
					lc = lc + dir[ldir][1];
				}

				// 오른쪽 검사
				int rdir = (i + 3) % 4;
				int rr = r + dir[rdir][0];
				int rc = c + dir[rdir][1];
				while (isIn(rr, rc) && (map[rr][rc] == '.' || map[rr][rc] == cur)) {
					if (map[rr][rc] == cur) {
						map[rr][rc] = map[x][y] = '.';
						System.out.println(map[x][y]);
						System.out.println(map[rr][rc]);
						return true;
					}
					rr = rr + dir[rdir][0];
					rc = rc + dir[rdir][1];
				}

				// 한 칸 전진
				r = r + dir[i][0];
				c = c + dir[i][1];

			}
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
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
}
