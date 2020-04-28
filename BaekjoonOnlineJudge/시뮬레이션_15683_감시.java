package _PRACTICE;

import java.io.*;
import java.util.*;

public class 시뮬레이션_15683_감시 {
	public static int N, M;
	public static int[][] map;
	public static int answer;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북동남서 방향

	public static Vector<cctv> list; // CCTV 종류와 좌표를 저장할 리스트

	public static class cctv {
		int num;
		int r;
		int c;

		public cctv(int num, int r, int c) {
			this.num = num;
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new Vector<>();
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					list.add(new cctv(map[i][j], i, j)); // 벡터에 CCTV 담기
			}
		}

		dfs(0);
		System.out.println(answer);
	}

	// 벡터의 인덱스를 활용하여 모든 CCTV 확인 체크
	public static void dfs(int index) {
		if (index == list.size()) { // 인덱스가 CCTV 총 개수와 같아졌을 때 (모든 CCTV를 검사했을 때) 0 개수 검사
			int cnt = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (map[i][j] == 0)
						cnt++;
			
			answer = answer > cnt ? cnt : answer;
			return;
		}

		cctv tv = list.get(index); // 벡터에서 해당 인덱스의 CCTV 꺼내기
		// CCTV 의 좌표 (r, c)
		int r = tv.r;
		int c = tv.c;

		// 현재의 맵 복사해두기
		int[][] copyMap = new int[N][M]; // 전역변수로 하지 않는 것 중요..!
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		switch (tv.num) { // CCTV 의 종류에 따라 맵 변경해주기
		case 1: // 0 1 2 3
			for (int i = 0; i < 4; i++) {
				move(i, r, c);
				dfs(index + 1);
				for (int j = 0; j < N; j++) {
					map[j] = copyMap[j].clone(); // 저장해놨던 맵으로 맵 되돌리기
				}
			}
			break;
		case 2: // 02 13 북남 동서
			for (int i = 0; i < 2; i++) {
				move(i, r, c);
				move(i + 2, r, c);
				dfs(index + 1);
				for (int j = 0; j < N; j++) {
					map[j] = copyMap[j].clone();
				}
			}
			break;
		case 3: // 01 12 23 30 북동 동남 남서 서북
			for (int i = 0; i < 4; i++) {
				move(i, r, c);
				move((i + 1) % 4, r, c); // 3->0
				dfs(index + 1);
				for (int j = 0; j < N; j++) {
					map[j] = copyMap[j].clone();
				}
			}
			break;
		case 4: // 012 123 230 301
			for (int i = 0; i < 4; i++) {
				move(i, r, c);
				move((i + 1) % 4, r, c); // 3->0
				move((i + 2) % 4, r, c); // 3->1
				dfs(index + 1);
				for (int j = 0; j < N; j++) {
					map[j] = copyMap[j].clone();
				}
			}
			break;
		case 5: // 0123
			for (int i = 0; i < 4; i++) {
				move(i, r, c); // 네 방향 모두 변경
			}
			dfs(index + 1);
			for (int j = 0; j < N; j++) {
				map[j] = copyMap[j].clone();
			}
			break;
		}

	}

	// 현재위치에서 방향에 따라 직진해서 변경하기!
	public static void move(int d, int r, int c) {
		while (true) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];

			if (!isIn(nr, nc) || map[nr][nc] == 6) { // 범위를 넘거나 벽을 만나면 종료
				break;
			}

			if (map[nr][nc] == 0)
				map[nr][nc] = -1; // 체크표시
			r = nr;
			c = nc;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
