import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_BOJ_2206_벽부수고이동하기 {
	public static int N, M;
	public static char[][] map;
	public static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		answer = 987654321;
		bfs();
		System.out.println(answer == 987654321 ? -1 : answer);
	}

	public static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(0, 0, 0, 1));
		boolean[][][] visited = new boolean[N][M][2]; // check 값을 분리해서 visited 저장
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (cur.x == N - 1 && cur.y == M - 1) {
				answer = Math.min(answer, cur.value); // check 값에 따라 최소값이 다를 수 있으므로, 바로 종료하면 안됨
				continue;
			}

			for (int i = 0; i < dir.length; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];

				if (!isIn(nx, ny) || visited[nx][ny][cur.check])
					continue;

				if (map[nx][ny] == '1' && cur.check == 0) { // 벽이면 부술 수 있는지 확인
					visited[nx][ny][1] = true;
					queue.add(new Node(nx, ny, 1, cur.value + 1));
				}

				if (map[nx][ny] == '0') {
					visited[nx][ny][cur.check] = true;
					queue.add(new Node(nx, ny, cur.check, cur.value + 1));
				}
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	public static class Node {
		int x, y, check, value; // check - 0,1

		public Node(int x, int y, int check, int value) {
			this.x = x;
			this.y = y;
			this.check = check;
			this.value = value;
		}
	}
}
