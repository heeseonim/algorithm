import java.util.*;

public class 블록이동하기 {
	public static void main(String[] args) {
		블록이동하기 블 = new 블록이동하기();
		System.out.println(블.solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 },
				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}

	public int[][] board;
	public boolean[][][] visit;
	public int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 동남서북
	public int[][] rdir = { { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } }; // 회전시 지나칠 대각선 위치
	public int N;

	public int solution(int[][] board) {
		this.board = board;
		N = board.length;

		visit = new boolean[N][N][4]; // 한칸좌표 & 방향

		return bfs();
	}

	public int bfs() {
		Queue<Robot> queue = new LinkedList<>();
		queue.add(new Robot(0, 0, 0, 0));
		visit[0][0][0] = true;

		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			// 방향 반대처리
			int reverseDir = (robot.d + 2) % 4;

			// 다른 한칸의 좌표
			int dr = robot.r + dir[robot.d][0];
			int dc = robot.c + dir[robot.d][1];
			visit[dr][dc][reverseDir] = true;

			if (isFinish(robot.r, robot.c) || isFinish(dr, dc)) {
				return robot.time;
			}

			// 상하좌우 이동 (로봇 모양 그대로)
			for (int i = 0; i < dir.length; i++) {
				int nr = robot.r + dir[i][0];
				int nc = robot.c + dir[i][1];
				int ndr = dr + dir[i][0];
				int ndc = dc + dir[i][1];

				if (!isIn(nr, nc) || !isIn(ndr, ndc) || board[nr][nc] == 1 || board[ndr][ndc] == 1
						|| visit[nr][nc][robot.d])
					continue;

				visit[nr][nc][robot.d] = true;
				queue.add(new Robot(nr, nc, robot.d, robot.time + 1));
			}

			// (r, c) 기준 회전

			// 1 시계방향, 3 반시계방향
			for (int i = 1; i < 4; i += 2) {
				int ndir = (robot.d + i) % 4;
				// 스쳐가는 좌표 - 1, 3 경우 다름
				int rr = robot.r + rdir[ndir][0];
				int rc = robot.c + rdir[ndir][1];
				if (i == 3) {
					rr = robot.r + rdir[robot.d][0];
					rc = robot.c + rdir[robot.d][1];
				}
				// 다른 한점의 이동 후 좌표
				int fr = robot.r + dir[ndir][0];
				int fc = robot.c + dir[ndir][1];

				if (!isIn(rr, rc) || !isIn(fr, fc) || board[rr][rc] == 1 || board[fr][fc] == 1
						|| visit[robot.r][robot.c][ndir])
					continue;

				visit[robot.r][robot.c][ndir] = true;
				queue.add(new Robot(robot.r, robot.c, ndir, robot.time + 1));
			}

			// (dr, dc) 기준으로 회전

			// 1 시계방향, 3 반시게방향
			for (int i = 1; i < 4; i += 2) {
				int ndir = (reverseDir + i) % 4;
				// 스쳐가는 좌표
				int rr = dr + rdir[ndir][0];
				int rc = dc + rdir[ndir][1];
				if (i == 3) {
					rr = dr + rdir[reverseDir][0];
					rc = dc + rdir[reverseDir][1];
				}
				// 다른 한점의 이동 후 좌표
				int fr = dr + dir[ndir][0];
				int fc = dc + dir[ndir][1];

				if (!isIn(rr, rc) || !isIn(fr, fc) || board[rr][rc] == 1 || board[fr][fc] == 1 || visit[dr][dc][ndir])
					continue;

				visit[dr][dc][ndir] = true;
				queue.add(new Robot(dr, dc, ndir, robot.time + 1));
			}
		}

		return -1;
	}

	public boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public boolean isFinish(int r, int c) {
		return r == N - 1 && c == N - 1;
	}

	public class Robot {
		int r;
		int c;
		int d;
		int time;

		public Robot(int r, int c, int d, int time) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.time = time;
		}
	}
}
