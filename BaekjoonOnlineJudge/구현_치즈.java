import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2636_치즈 {
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static boolean[][] visited;
	public static boolean[][] airVisited;
	public static int[][] map;
	public static int count;
	public static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		airVisited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 외부 공기 값 바꿔줌
		map[0][0] = -1;
		airVisited[0][0] = true;
		change(0, 0);

		int time = 0;
		int answer = 0;
		while (true) {
			flag = false;
			count = 0; // 치즈 수 초기화
			// -1인 위치에서 치즈 찾기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == -1) {
						Cheese(i, j);
					}
				}
			}

			if (!flag) { // 치즈 없으면 탈출
				break;
			}

			// 치즈 녹이기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (visited[i][j]) { // 녹아야 할 치즈라면
						map[i][j] = -1;
					}
				}
			}

			airVisited = new boolean[R][C];
			change(0, 0); // 외부공기 다시 체크

			time++;
			answer = count;
		}
		System.out.println(time);
		System.out.println(answer);

	} // end of main

	// 외부 공기 값 바꿔줄 함수
	public static void change(int r, int c) {

		for (int i = 0; i < 4; i++) {
			int newR = r + dir[i][0];
			int newC = c + dir[i][1];
			if (isIn(newR, newC) && map[newR][newC] <= 0 && !airVisited[newR][newC]) {
				map[newR][newC] = -1;
				airVisited[newR][newC] = true;
				change(newR, newC);
			}
		}
	}

	// 공기와 접촉된 치즈를 체크할 함수 - 개수 세기
	public static void Cheese(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int newR = r + dir[i][0];
			int newC = c + dir[i][1];
			// 범위 안에 있고, 이동한 좌표에 치즈가 존재, 이 치즈를 방문하지 않았으면
			if (isIn(newR, newC) && map[newR][newC] == 1 && !visited[newR][newC]) {
				visited[newR][newC] = true;
				count++; // 녹을 치즈 개수
				flag = true; // 치즈가 존재함
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
} // end of class
