import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_3055_탈출 {
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static char[][] map;
	public static Queue<Point> w;
	public static Queue<Point> s;

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		w = new LinkedList<>(); // 물 큐
		s = new LinkedList<>(); // 고슴도치 큐

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					w.add(new Point(i, j));
				} else if (map[i][j] == 'S') {
					s.add(new Point(i, j));
				}
			}
		}

		int time = 0;
		while (true) {
			time++;
			if (s.size() == 0) { // 고슴도치가 더 이상 갈 수 없을 때
				System.out.println("KAKTUS");
				return;
			}

			waterBFS(); // 먼저 물 뿌려주기
			if (sBFS()) { // 비버 굴 발견하면 return하여 시간 출력
				System.out.println(time);
				return;
			}
		}

	} // end of main

	// 물 이동
	public static void waterBFS() {
		int size = w.size();
		for (int i = 0; i < size; i++) {
			Point p = w.poll();

			for (int j = 0; j < 4; j++) {
				int newR = p.x + dir[j][0];
				int newC = p.y + dir[j][1];
				if (isIn(newR, newC) && map[newR][newC] == '.') {
					map[newR][newC] = '*';
					w.add(new Point(newR, newC));
				}
			}
		}
	}

	// 고슴도치 이동
	public static boolean sBFS() {
		int size = s.size();
		for (int i = 0; i < size; i++) {
			Point p = s.poll();

			for (int j = 0; j < 4; j++) {
				int newR = p.x + dir[j][0];
				int newC = p.y + dir[j][1];
				if (isIn(newR, newC)) {
					if (map[newR][newC] == 'D') {
						return true; // 굴 발견 시 while문 종료되도록 리턴
					} else if (map[newR][newC] == '.') {
						map[newR][newC] = 'S'; // 고슴도치 이동 표시
						s.add(new Point(newR, newC));
					}
				}
			}
		}
		return false;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
} // end of class
