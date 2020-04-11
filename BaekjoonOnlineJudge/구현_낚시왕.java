
/**
 * 백준 17143 낚시왕
 * 
 * 상어 : 크기, 속도 + 방향?
 * 
 * 낚시왕의 초기 위치 (0, 0) 맵은 (1, 1) 부터 시작
 * (0, C) 칸에 도착하면 이동을 멈춤
 * 
 * 1. 낚시왕이 오른쪽으로 한 칸 이동
 * 2. 열에 있는 상어 중에서 제일 가까운 상어 잡는다. 상어는 잡히면 사라짐
 * 3. 상어가 이동
 * 
 * 1. 상어는 주어진 속도로 이동
 * 2. 속도의 단위는 칸/초
 * 3. 경계 넘으면 방향 반대로 이동
 * 
 * 1. 이동을 마친 후 한 칸에 두마리 이상 있을 수 없음 - 가장 큰 상어가 나머지 모두 잡아먹음
 * 2. 낚시왕이 잡은 상어 크기의 합
 * 
 * 1. 낚시왕이 이동을 멈출 때까지 진행
 * 2. 칸의 상태는 상어가 이동을 마친 후 (1초뒤) 검사
 * 
 * 1. 낚시왕이 먼저 이동 후 시작 (0, 1) -> 1차원 배열로 열만 관리
 * 반복
 * 2. 상어 이동
 * 3. 낚시왕 이동
 * 
 * 1. 격자판 크기 R, C 상어 수 M
 * 		map[R+1][C+1]
 * 2. r, c, s, d, z : (r,c) 상어위치, s 속력, d 방향, z 크기
 * 3. d : 1위 2아래 3오 4왼
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BP_낚시왕 {
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static class shark implements Comparable<shark> {
		int v;
		int d;
		int size;

		public shark(int v, int d, int size) {
			this.v = v;
			this.d = d;
			this.size = size;
		}

		@Override
		public int compareTo(shark o) {
			return o.size - this.size; // 사이즈 역순정렬
		}
	}

	static List<shark>[][] map;
	static boolean[] visited;
	static int R, C, M, king, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new ArrayList[R + 1][C + 1];
		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				map[i][j] = new ArrayList<>();

		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			map[r][c].add(new shark(s, d, z));
		}

		king = 1; // 낚시왕의 위치
		answer = 0; // 낚시왕이 잡은 물고기 크기 합
		while (king <= C) {
			getFish();
			moveShark();
			king += 1;
		}

		System.out.println(answer);

	}

	static void getFish() {
		int position = king;
		// 크기가 1이상인 곳을 발견했다면 잡고 멈추기
		for (int i = 1; i <= R; i++)
			if (!map[i][position].isEmpty()) {
				answer += map[i][position].get(0).size;
				map[i][position].clear();
				return;
			}

	}

	static void moveShark() {
		visited = new boolean[10001];
		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				if (!map[i][j].isEmpty() && !visited[map[i][j].get(0).size]) { // 상어가 존재하고, 방문하지 않았다면
					shark s = map[i][j].get(0);
					map[i][j].remove(0);
					// 갖고 있는 속력만큼 칸 이동
					move(i, j, s);
				}

		// 물고기 크기 역순 정렬
		sortShark();
	}

	static void move(int r, int c, shark s) {
		int direction = s.d;
		int nr = r, nc = c;

		for (int i = 0; i < s.v; i++) {
			// 상어가 끝에 있다면 방향 바꿔주기
			if (checkChange(direction, nr, nc)) // 경계를 벗어날 것으로 예상된다면 방향 바꿔주기
				direction = changeDir(direction);

			nr = nr + dir[direction][0];
			nc = nc + dir[direction][1];
		}

		s.d = direction;
		map[nr][nc].add(s);
		visited[s.size] = true; // 해당 사이즈의 상어는 방문처리
	}

	static void sortShark() {
		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				if (!map[i][j].isEmpty()) {
					Collections.sort(map[i][j]);
					if (map[i][j].size() > 1) { // 작은 상어 없애기
						shark s = map[i][j].get(0);
						map[i][j].clear();
						map[i][j].add(s);
					}
				}
	}

	static int changeDir(int direction) {
		if (direction % 2 == 0)
			return direction - 1;
		else
			return direction + 1;
	}

	static boolean checkChange(int direction, int r, int c) {
		switch (direction) {
		case 1:
			if (r == 1)
				return true;
			break;
		case 2:
			if (r == R)
				return true;
			break;
		case 3:
			if (c == C)
				return true;
			break;
		case 4:
			if (c == 1)
				return true;
			break;
		}
		return false;
	}
}
