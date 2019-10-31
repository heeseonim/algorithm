import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_16236_아기상어 {
	public static int N;
	public static int[][] map;
	public static int[][] visited;
	public static int[] fish;
	public static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	public static int eatFish, size, time;
	public static int r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N]; // 방문배열로 시간 계산
		fish = new int[7]; // 물고기 1~6
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					r = i;
					c = j;
					map[i][j] = 0;
				}
				if (map[i][j] > 0) {
					fish[map[i][j]]++;
				}
			}
		}

		eatFish = 0; // 먹은 물고기
		size = 2; // 상어 사이즈
		time = 0;

		while (true) {
			boolean flag = false;
			for (int i = 1; i < size; i++) {
				if (fish[i] > 0) {
					flag = true;
					break;
				}
			}
			if (!flag) { // 먹을 수 있는 물고기 없음
				break;
			}
			if (!bfs()) { // 물고기 존재하나 도달할 수 없음
				break;
			}
		}

		System.out.println(time);
	}

	public static Comparator<int[]> comparator = new Comparator<int[]>() {

		@Override
		public int compare(int[] a, int[] b) { // [0] : r, [1] : c
			// 방문했던 곳은 뒤로
			if (visited[a[0]][a[1]] != visited[b[0]][b[1]]) {
				return visited[a[0]][a[1]] - visited[b[0]][b[1]];
			}
			if (a[0] != b[0]) {
				return a[0] - b[0]; // 위쪽 먹이 먼저
			}
			return a[1] - b[1]; // 왼쪽 먹이는 다음
		}
	};

	public static boolean bfs() {
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], 0);
		}
		PriorityQueue<int[]> q = new PriorityQueue<>(comparator);
		visited[r][c] = 1;
		q.offer(new int[] { r, c });
		while (q.size() > 0) {
			int[] t = q.poll();
			r = t[0];
			c = t[1];

			if (0 < map[r][c] && map[r][c] < size) {
				fish[map[r][c]]--;
				eatFish++;
				time += visited[r][c] - 1;
				if (eatFish == size) {
					size++;
					eatFish = 0;
				}
				map[r][c] = 0;
				return true; // 먹었음
			}
			for (int i = 0; i < dir.length; i++) {
				int newR = r + dir[i][0];
				int newC = c + dir[i][1];
				if (isIn(newR, newC)) {
					if (visited[newR][newC] == 0 && map[newR][newC] <= size) { // 방문안했고, 이동할 수 있는 칸이면
						visited[newR][newC] = visited[r][c] + 1; // 이동거리값 누적
						q.offer(new int[] { newR, newC }); // 큐에 담아주기
					}
				}
			}
		}
		return false; // 못먹었음
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
