import java.io.*;
import java.util.*;

public class BFS_2933_미네랄 {
	public static int R, C, N;
	public static char[][] map;
	// 상, 우, 하, 좌
	public static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			// 1. 미네랄 파괴
			breaking(i % 2, Integer.parseInt(st.nextToken()));
			// 2. 공중 클러스터 확인
			search();
		}

		for (int i = 0; i < R; i++) {
			System.out.println(map[i]);
		}
	}

	// 1. 미네랄 파괴
	public static void breaking(int nmg, int height) {
		if (nmg == 0) { // 왼쪽에서 공격 : 0,2,4,...
			for (int i = 0; i < C; i++) {
				if (map[R - height][i] == 'x') {
					map[R - height][i] = '.';
					return; // 하나만 없애야함
				}
			}
		} else {
			for (int i = C - 1; i >= 0; i--) {
				if (map[R - height][i] == 'x') {
					map[R - height][i] = '.';
					return;
				}
			}
		}
	}

	// 2. 공중 클러스터 탐색
	public static void search() {
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] == 'x') {
					if (BFS(i, j))
						return;
				}
			}
		}
	}

	// 연결된 클러스터 탐색 & 떠있는지 확인
	public static boolean BFS(int r, int c) {
		List<Node> list = new ArrayList<Node>(); // 떠있다면 내리는 함수에서 사용
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c));
		visited[r][c] = true;
		int max = 0;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			list.add(cur);

			for (int i = 0; i < dir.length; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == '.')
					continue;

				max = Math.max(nr, max);
				visited[nr][nc] = true;
				queue.add(new Node(nr, nc));
			}
		}

		if (max != (R - 1)) {// 공중에 떠있을 경우
			down(list);
			return true; // 찾았다면 클러스터 탐색 종료
		}
		return false;
	}

	// 3. 내릴 수 있을 때까지 내리기
	public static void down(List<Node> list) {
		// 옮기기 전 있던 자리 . 으로 초기화
		for (Node cur : list) {
			map[cur.r][cur.c] = '.';
		}

		// 한칸씩 내려보며 만나는 경우 찾기
		int move = 1;
		ex: while (true) {
			for (Node cur : list) {
				// 바닥을 넘어가거나, 다음에 x 가 있다면
				if (cur.r + move == R || map[cur.r + move][cur.c] == 'x') {
					move--; // 그 전까지 내릴 수 있음
					break ex;
				}
			}
			move++;
		}

		for (Node cur : list) {
			map[cur.r + move][cur.c] = 'x';
		}
	}

	public static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}

