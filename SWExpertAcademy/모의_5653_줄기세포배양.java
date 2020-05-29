
/**
 * 
 * @author hisun
 * 
 * 줄기세포들을 배양 용기에 도포한 후 일정 시간 동안 배양을 시킨 후 줄기 세포의 개수가 몇 개가 되는지 계산
 * 
 * 생명력이라는 수치를 가지고 있다.
 * 초기 상태에서 줄기 세포들은 비활성 상태
 * 생명력 수치가 X인 줄기 세포의 경우 X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태
 * 
 * 활성 상태가 되면 X시간 동안 살아있을 수 있으며 X시간이 지나면 세포는 죽게 된다
 * 죽은 상태로 해당 그리드 셀을 차지하게 된다
 * 
 * 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식
 * 번식된 줄기 세포는 비활성 상태
 * 번식하는 방향에 이미 줄기 세포가 존재하는 경우 해당 방향으로 추가적으로 번식하지 않는다
 * 
 * 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우
 * 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지하게 된다.
 * 
 * 시뮬레이션에서 배양 용기의 크기는 무한하다고 가정
 * 
 * 줄기 세포의 초기 상태 정보와 배양 시간 K시간
 * K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수를 구하는 프로그램
 * 
 * 초기 상태에서 줄기 세포가 분포된 영역의 넓이는 세로 크기 N, 가로 크기 M
 * 생명력 X는 1 이상 10 이하의 정수
 * 
 */

import java.io.*;
import java.util.*;

public class 줄기세포배양_5653 {
	public static int[][] map;
	public static boolean[][] visit;
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int N, M, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N + K][M + K];
			visit = new boolean[N + K][M + K];

			Queue<Cell> queue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < M; j++) {
					int a = i + K / 2;
					int b = j + K / 2;
					map[a][b] = Integer.parseInt(st.nextToken());
					if (map[a][b] != 0) {
						visit[a][b] = true;
						queue.add(new Cell(a, b, map[a][b]));
					}
				}
			}

			while (K-- > 0) {
				int len = queue.size();

				// 동시 번식 경우를 고려하여 값들을 처리
				for (Cell c : queue)
					if (c.status == 1)
						check(c);

				for (int i = 0; i < len; i++) {
					Cell c = queue.poll();

					if (c.status == 1) {
						for (int j = 0; j < dir.length; j++) {
							int nx = c.x + dir[j][0];
							int ny = c.y + dir[j][1];

							if (visit[nx][ny])
								continue;

							queue.add(new Cell(nx, ny, map[nx][ny]));
							visit[nx][ny] = true;
						}
					}

					c.change();

					if (c.status == 2) // 죽은 상태 제외
						continue;

					queue.add(c);
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(queue.size());
			System.out.println(sb.toString());
		}
	}

	public static void check(Cell c) {
		for (int i = 0; i < dir.length; i++) {
			int nx = c.x + dir[i][0];
			int ny = c.y + dir[i][1];

			if (visit[nx][ny])
				continue;

			if (map[nx][ny] < c.hp)
				map[nx][ny] = c.hp;
		}
	}

	public static class Cell {
		int x, y;
		int hp;
		int time;
		int status; // 비활성 0 활성 1 죽음 2

		public Cell(int x, int y, int hp) {
			this.x = x;
			this.y = y;
			this.hp = hp;
			// 초기상태
			this.time = 0;
			this.status = 0;
		}

		public void change() {
			time++;

			if (hp == time)
				status = 1;
			else if (hp * 2 <= time)
				status = 2;
		}

	}

}
