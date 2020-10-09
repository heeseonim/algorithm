import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_19236_청소년상어 {
	public static int[][] map; // 전체 맵
	public static Fish[] fishes; // 물고기 배열
	public static boolean[] isAlive; // 물고기의 상태
	public static int[][] dir = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } }; // 8방향 배열
	public static int answer;

	public static class Fish { // 물고기 객체
		int x;
		int y;
		int d;

		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[4][4];
		fishes = new Fish[17]; // 1~16 번의 번호를 가짐
		isAlive = new boolean[17];
		Arrays.fill(isAlive, true);
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken()); // 물고기의 번호
				int dir = Integer.parseInt(st.nextToken()) - 1; // 물고기의 방향
				map[i][j] = num;
				fishes[num] = new Fish(i, j, dir); // 물고기 배열에 물고기 저장
			}
		}

		// (0, 0) 위치에 있는 물고기부터 먹고 시작
		int initFish = map[0][0];
		map[0][0] = -1;
		isAlive[initFish] = false;
		eatFish(0, 0, fishes[initFish].d, initFish);

		System.out.println(answer);
	}

	public static void eatFish(int r, int c, int d, int sum) {
		// 맵 복사해두기
		Fish[] copyFishes = fishes.clone();
		int[][] copyMap = new int[4][4];
		for (int i = 0; i < copyMap.length; i++)
			copyMap[i] = map[i].clone();

		fishMove(); // 물고기 이동

		for (int i = 1; i <= 3; i++) { // 상어 이동
			int nr = r + dir[d][0] * i;
			int nc = c + dir[d][1] * i;

			if (!isIn(nr, nc) || map[nr][nc] == 0)
				continue; // 범위 밖을 벗어나거나 빈칸이면 continue

			map[r][c] = 0; // 상어 위치 빈칸으로 변경
			int moveNum = map[nr][nc]; // 물고기 번호 저장
			isAlive[map[nr][nc]] = false; // 이동한 위치의 물고기 죽음
			map[nr][nc] = -1; // 이동할 곳에 상어 표시

			eatFish(nr, nc, fishes[moveNum].d, sum + moveNum);

			// eatFish 처리가 완료된 후 (물고기배열,전체맵 복원) 돌아왔을 때, 출발 시 먹은 물고기 복원
			map[nr][nc] = moveNum;
			isAlive[moveNum] = true;
			map[r][c] = -1; // 상어 위치 복원
		}
		

		// 먹은 후 최댓값 계산
		answer = answer < sum ? sum : answer;

		// 물고기 배열, 전체 맵 복원
		fishes = copyFishes.clone();
		for (int i = 0; i < map.length; i++)
			map[i] = copyMap[i].clone();
	}

	public static void fishMove() {
		for (int num = 1; num <= 16; num++) {
			if (!isAlive[num])
				continue;

			Fish cur = fishes[num];
			int nr = cur.x + dir[cur.d][0];
			int nc = cur.y + dir[cur.d][1];

			// 반복
			int nd = cur.d;
			boolean isExist = false;
			int cnt = 0;

			while (cnt < 8)
				if (!isIn(nr, nc) || map[nr][nc] == -1) { // 방향 벗어나거나 상어가 있다면
					nd = (nd + 1) % 8; // 방향변경
					nr = cur.x + dir[nd][0];
					nc = cur.y + dir[nd][1];
					cnt++;
				} else { // 빈칸이거나 다른 물고기가 있을 경우
					isExist = true;
					break;
				}

			if (!isExist) { // 이동할 수 있는 칸이 없을 때, 방향만 바꿔두기
				fishes[num].d = nd;
				continue;
			}

			// 이동할 수 있는 칸이 존재할 때
			if (map[nr][nc] != 0) { // 물고기라면
				// 원래 있던 물고기 위치 바꿔줌
				fishes[map[nr][nc]] = new Fish(cur.x, cur.y, fishes[map[nr][nc]].d);
				map[cur.x][cur.y] = map[nr][nc];
			} else // 빈칸이라면
				map[cur.x][cur.y] = 0;

			// 현재의 물고기 정보 갱신
			fishes[num] = new Fish(nr, nc, nd);
			map[nr][nc] = num;
			
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
	}
}
