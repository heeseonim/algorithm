package _PRACTICE;

import java.io.*;
import java.util.*;

public class 시뮬레이션_로봇청소기 {
	public static int N, M;
	public static int[][] map;
	public static boolean[][] visited;
	public static int curDir, answer;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 0123 방향

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		move(r, c, d);
		System.out.println(answer);
	}

	public static void move(int r, int c, int d) {
		// 현재 자리 청소
		if (!visited[r][c]) {
			visited[r][c] = true;
			answer++;
		}

		curDir = d; // 현재 방향 저장
		while (true) {
			int nd = changeD(d); // 왼쪽으로 회전

			int nr = r + dir[nd][0];
			int nc = c + dir[nd][1];
			if (map[nr][nc] == 0 && !visited[nr][nc]) {// 빈칸이고, 청소하지 않았다면 함수호출
				move(nr, nc, nd);
				break;
			} else { // 청소했거나, 벽일 때
				if (nd == curDir) { // 현재 방향으로 돌아왔다면 후진
					int bd = backDir(nd);
					int br = r + dir[bd][0];
					int bc = c + dir[bd][1];
					if (map[br][bc] == 0) {
						move(br, bc, nd); // 방향 유지한 채로 후진
						break;
					} else {
						return; // 로봇 이동 종료
					}
				} else {
					d = nd;
				}
			}

		}
	}

	// 왼쪽으로 회전
	public static int changeD(int d) {
		if (d == 0)
			return 3;
		else
			return d - 1;
	}

	// 후진
	public static int backDir(int d) {
		if (d >= 2)
			return d - 2;
		else
			return d + 2;
	}
}
