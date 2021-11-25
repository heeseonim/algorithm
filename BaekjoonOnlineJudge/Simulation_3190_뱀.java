import java.io.*;
import java.util.*;

public class 시뮬레이션_3190_뱀 {
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		int appleNum = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < appleNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 사과 위치 표시
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 3;
		}

		int snakePath = Integer.parseInt(br.readLine());
		HashMap<Integer, Character> hm = new HashMap<>();
		for (int i = 0; i < snakePath; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			hm.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}

		int time = 0;
		int d = 1;
		int x = 0;
		int y = 0;

		Queue<Point> queue = new LinkedList<>(); // 뱀의 좌표를 저장할 리스트
		map[0][0] = 1;
		queue.add(new Point(0, 0));

		while (true) {
			if (hm.get(time) != null)
				if (hm.get(time) == 'L')
					if (d == 0)
						d = 3;
					else
						d -= 1;
				else
					d = (d + 1) % 4;

			int nx = x + dir[d][0];
			int ny = y + dir[d][1];

			if (!isIn(nx, ny) || map[nx][ny] == 1) {
				System.out.println(time + 1);
				break;
			}

			// 사과없을 때 꼬리 지우기
			if (map[nx][ny] != 3) {
				Point p = queue.poll();
				map[p.x][p.y] = 0;
			}

			map[nx][ny] = 1;
			queue.add(new Point(nx, ny));

			x = nx;
			y = ny;
			time++;
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
