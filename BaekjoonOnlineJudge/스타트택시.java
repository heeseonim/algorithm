import java.io.*;
import java.util.*;

public class 스타트택시 {
	public static class person {
		int num;
		int sx, sy, ex, ey;

		public person(int num, int sx, int sy, int ex, int ey) {
			this.num = num;
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
	}

	public static class taxi {
		int x, y;
		int move;

		public taxi(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}

	public static int[][] map;
	public static person taken; // 현재 타고 있는 승객
	public static int N, M, fuel;
	public static HashMap<Integer, person> customers;
	public static taxi driver;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static boolean[] isArrived;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		driver = new taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

		isArrived = new boolean[M];
		customers = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			map[sx][sy] = i + 2;
			customers.put(i + 2, new person(i + 2, sx, sy, ex, ey));
		}

		taken = null;
		solve();
	}

	// 전체 동작 함수
	public static void solve() {
		while (!customers.isEmpty()) {
			int startFuel = bfs();
			fuel -= startFuel;
			if (fuel < 0)
				break;

			int endFuel = bfs();
			fuel -= endFuel;
			if (fuel < 0)
				break;

			// -가 되지 않았다면 잘 도착한 것으로, 도착지까지 간 거리 *2 해주기
			fuel += endFuel * 2;
		}

		System.out.println(fuel < 0 ? -1 : fuel);
	}

	// 거리 계산
	public static int bfs() {
		Queue<taxi> tQueue = new LinkedList<>();
		Queue<person> candidates = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];

		int prevMove = driver.move;
		visit[driver.x][driver.y] = true;
		tQueue.add(driver);

		while (!tQueue.isEmpty()) {
			taxi t = tQueue.poll();

			if (fuel - t.move < 0) { // 이동 중에 연료 떨어지면 종료
				return Integer.MAX_VALUE;
			}

			if (prevMove != t.move && !candidates.isEmpty()) { // 움직인 거리가 다르고, 이미 후보들이 있으면
				break;
			}

			prevMove = t.move; // 현재까지 움직인 거리 갱신

			if (taken == null) { // 승객이 타고 있지 않다면
				// 해당 위치에 승객이 존재하는지 검사
				if (map[t.x][t.y] > 1) { // 있다면 후보 큐에 넣기
					candidates.add(customers.get(map[t.x][t.y]));
				}
			} else { // 승객이 타고 있다면
				person cur = customers.get(taken.num);
				if (t.x == cur.ex && t.y == cur.ey) { // 도착했다면
					customers.remove(cur.num);
					taken = null;
					driver = new taxi(t.x, t.y, 0); // 택시 초기화
					return prevMove; // 도착지까지 간 거리 리턴
				}
			}

			for (int i = 0; i < dir.length; i++) {
				int nx = t.x + dir[i][0];
				int ny = t.y + dir[i][1];

				if (!isIn(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])
					continue;

				tQueue.add(new taxi(nx, ny, t.move + 1));
				visit[nx][ny] = true;
			}
		}
		
		// while 문 끝났는데 승객후보 없으면 벽에 막힌 것, -1 출력해야함
		if (candidates.isEmpty())
			return Integer.MAX_VALUE;

		// 승객 태우기
		taken = compare(candidates);
		map[taken.sx][taken.sy] = 0;
		driver = new taxi(taken.sx, taken.sy, 0); // 택시 위치, 움직인 거리 초기화
		return prevMove;
	}

	// 승객 후보 우선순위 비교
	public static person compare(Queue<person> candidates) {
		person first = candidates.poll();

		while (!candidates.isEmpty()) {
			person second = candidates.poll();

			if (first.sx > second.sx) {
				first = second;
			} else if (first.sx == second.sx && first.sy > second.sy) {
				first = second;
			}
		}

		return first;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
