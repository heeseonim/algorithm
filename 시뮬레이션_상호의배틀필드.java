import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873_상호의배틀필드 {
	public static char[][] map;
	public static int carI, carJ;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			carI = 0;
			carJ = 0;
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					char a = str.charAt(j);
					if (a == '<' || a == '>' || a == 'v' || a == '^') {
						carI = i;
						carJ = j;
					}
					map[i][j] = a;
				}
			}

			int N = Integer.parseInt(br.readLine()); // 입력의 수
			String input = br.readLine(); // 사용자가 넣을 입력
			for (int i = 0; i < N; i++) {
				// 전차의 위치와 사용자의 입력을 전달
				changeMap(input.charAt(i));
			}
			System.out.print("#" + tc + " ");
			for (char[] ma : map) {
				for (char m : ma) {
					System.out.print(m);
				}
				System.out.println();
			}
		}
	} // end of main

	public static void changeMap(char input) {
		// 현재 전차의 방향
		char dir = map[carI][carJ];
		switch (input) {
		case 'U':
			// 평지라면 전차 이동
			if (carI - 1 >= 0 && map[carI - 1][carJ] == '.') {
				map[carI][carJ] = '.';
				map[carI - 1][carJ] = '^';
				carI--; // 전차의 위치 변경
			} else { // 이동 못한다면 방향만 변경
				map[carI][carJ] = '^';
			}
			break;
		case 'D':
			if (carI + 1 < map.length && map[carI + 1][carJ] == '.') {
				map[carI][carJ] = '.';
				map[carI + 1][carJ] = 'v';
				carI++; // 전차의 위치 변경
			} else { // 이동 못한다면 방향만 변경
				map[carI][carJ] = 'v';
			}
			break;
		case 'L':
			if (carJ - 1 >= 0 && map[carI][carJ - 1] == '.') {
				map[carI][carJ] = '.';
				map[carI][carJ - 1] = '<';
				carJ--; // 전차의 위치 변경
			} else { // 이동 못한다면 방향만 변경
				map[carI][carJ] = '<';
			}
			break;
		case 'R':
			if (carJ + 1 < map[0].length && map[carI][carJ + 1] == '.') {
				map[carI][carJ] = '.';
				map[carI][carJ + 1] = '>';
				carJ++; // 전차의 위치 변경
			} else { // 이동 못한다면 방향만 변경
				map[carI][carJ] = '>';
			}
			break;
		case 'S':
			moveBoom(dir);
			break;
		}
	}

	public static void moveBoom(char dir) {
		// 전차의 위치에서 시작
		int boomI = carI;
		int boomJ = carJ;
		if (dir == '^') {
			while (boomI - 1 >= 0) {
				// 벽이거나 평지면
				if (map[boomI - 1][boomJ] == '*') {
					map[boomI - 1][boomJ] = '.';
					break;
				} else if (map[boomI - 1][boomJ] == '-' || map[boomI-1][boomJ] == '.') {
					boomI--;
				} else if (map[boomI - 1][boomJ] == '#') { // 강철을 만났을 땐 변화 X
					break; // 포탄 진행 종료
				}
			}
		} else if (dir == 'v') {
			while (boomI + 1 < map.length) {
				if (map[boomI + 1][boomJ] == '*') {
					map[boomI + 1][boomJ] = '.';
					break;
				} else if (map[boomI + 1][boomJ] == '-' || map[boomI + 1][boomJ] == '.') {
					boomI++;
				} else if (map[boomI + 1][boomJ] == '#') { // 강철을 만났을 땐 변화 X
					break; // 포탄 진행 종료
				}
			}
		} else if (dir == '<') {
			while (boomJ - 1 >= 0) {
				if (map[boomI][boomJ - 1] == '*') {
					map[boomI][boomJ - 1] = '.';
					break;
				} else if (map[boomI][boomJ - 1] == '-' || map[boomI][boomJ - 1] == '.') {
					boomJ--;
				} else if (map[boomI][boomJ - 1] == '#') { // 강철을 만났을 땐 변화 X
					break; // 포탄 진행 종료
				}
			}
		} else if (dir == '>') {
			while (boomJ + 1 < map[0].length) {
				if (map[boomI][boomJ + 1] == '*') {
					map[boomI][boomJ + 1] = '.';
					break;
				} else if (map[boomI][boomJ + 1] == '-' || map[boomI][boomJ + 1] == '.') {
					boomJ++;
				} else if (map[boomI][boomJ + 1] == '#') { // 강철을 만났을 땐 변화 X
					break; // 포탄 진행 종료
				}
			}
		}
	}

} // end of class
