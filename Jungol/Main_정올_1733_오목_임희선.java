package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_1733_오목_임희선 {
	public static int[][] map;

	// jungol 1733 오목
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[19][19];
		StringTokenizer st;
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (map[i][j] > 0) {
					if (right(i, j, map[i][j]) || down(i, j, map[i][j]) || rd(i, j, map[i][j])) {
						System.out.println(map[i][j]);
						System.out.println((i + 1) + " " + (j + 1));
						return;
					} else if (ld(i, j, map[i][j])) {
						System.out.println(map[i][j]);
						System.out.println((i + 5) + " " + (j - 3));
						return;
					}
				}
			}
		}
		System.out.println(0);

	}

	// 가로, 세로, 대각선 방향 탐색
	// 연속 5알일 때 승리, 6알 이상이면 승리X

	public static boolean right(int r, int c, int num) {
		if (c + 4 >= map.length)
			return false;

		int temp = c;
		while (temp <= c + 4) {
			if (map[r][temp++] != num)
				return false;
		}

		if (c - 1 >= 0 && map[r][c - 1] == num)
			return false;

		if (c + 5 < map.length && map[r][c + 5] == num)
			return false;

		return true;
	}

	public static boolean down(int r, int c, int num) {
		if (r + 4 >= map.length)
			return false;

		int temp = r;
		while (temp <= r + 4) {
			if (map[temp++][c] != num)
				return false;
		}

		if (r - 1 >= 0 && map[r - 1][c] == num)
			return false;

		if (r + 5 < map.length && map[r + 5][c] == num)
			return false;

		return true;
	}

	public static boolean rd(int r, int c, int num) {
		if (r + 4 >= map.length || c + 4 >= map.length)
			return false;

		int tempR = r, tempC = c;
		while (tempR <= r + 4 && tempC <= c + 4) {
			if (map[tempR++][tempC++] != num)
				return false;
		}

		if (r - 1 >= 0 && c - 1 >= 0 && map[r - 1][c - 1] == num)
			return false;

		if (r + 5 < map.length && c + 5 < map.length && map[r + 5][c + 5] == num)
			return false;

		return true;
	}

	public static boolean ld(int r, int c, int num) {
		if (r + 4 >= map.length || c - 4 < 0)
			return false;

		int tempR = r, tempC = c;
		while (tempR <= r + 4 && tempC >= c - 4) {
			if (map[tempR++][tempC--] != num)
				return false;
		}

		if (r - 1 >= 0 && c + 1 < map.length && map[r - 1][c + 1] == num)
			return false;

		if (r + 5 < map.length && c - 5 >= 0 && map[r + 5][c - 5] == num)
			return false;

		return true;
	}
}
