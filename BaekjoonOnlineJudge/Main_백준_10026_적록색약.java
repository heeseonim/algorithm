import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_백준_10026_적록색약 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		System.out.print(bfs() + " ");

		// G를 R로 바꿔줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') { // G를 R로 바꿔줌
					map[i][j] = 'R';
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
		// 적록색약인 사람이 봤을 때
		System.out.print(bfs());

	}

	// 그룹 수 반환하기
	static int bfs() {
		int group = 0;
		Queue<Point> queue = new LinkedList<Point>();
		// 배열을 순회하면서 그룹 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue; // 방문한 좌표면 다음 좌표로 넘어감
				queue.add(new Point(i, j));
				visited[i][j] = true;

				while (!queue.isEmpty()) {
					Point cur = queue.poll();

					for (int k = 0; k < 4; k++) {
						int nextI = cur.x + dir[k][0];
						int nextJ = cur.y + dir[k][1];

						if (isIn(nextI, nextJ) && !visited[nextI][nextJ] && map[nextI][nextJ] == map[i][j]) {
							queue.add(new Point(nextI, nextJ));
							visited[nextI][nextJ] = true;
						}
					}
				}
				group++; // 동일문자를 담은 큐가 비어지면 그룹 수 + 1
			}
		}

		return group;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
