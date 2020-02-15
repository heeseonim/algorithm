import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236 {
	public static class shark {
		int x;
		int y;
		int size;
		int eat;
		int dist;

		public shark(int x, int y, int size, int eat, int dist) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.dist = dist;
		}
	}

	public static class fish {
		int x;
		int y;
		int dist;

		public fish(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public static shark s;
	public static int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	public static int N;
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					s = new shark(i, j, 2, 0, 0);
					map[i][j] = 0;
				}
			}
		}

		bfs();
		System.out.println(s.dist);
	}

	public static void bfs() {
		while (true) {
			Queue<fish> moveQueue = new LinkedList<>();
			List<fish> fishList = new ArrayList<>();
			boolean[][] visited = new boolean[N][N];
			
			// 초기 상어의 위치를 넣어준다.
			moveQueue.add(new fish(s.x, s.y, 0));
			visited[s.x][s.y] = true;
			
			// 먹을 수 있는 물고기 찾기
			while (!moveQueue.isEmpty()) {
				fish cur = moveQueue.poll();
				
				for (int i = 0; i < dir.length; i++) {
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];

					if (!isIn(nx, ny) || map[nx][ny] > s.size || visited[nx][ny])
						continue;

					visited[nx][ny] = true;
					moveQueue.add(new fish(nx, ny, cur.dist+1)); // 거리 : 현재위치 + 1
					if (map[nx][ny] != 0 && map[nx][ny] < s.size) {
						fishList.add(new fish(nx, ny, cur.dist+1));
					}
				}
			}

			if (fishList.size() == 0) // 물고기가 없으면 종료
				break;
			else {
				if (fishList.size() >= 2) {
					// 물고기 리스트 정렬
					Collections.sort(fishList, new Comparator<fish>() {
						@Override
						public int compare(fish o1, fish o2) {
							if (o1.dist == o2.dist) {
								if (o1.x == o2.x)
									return o1.y - o2.y;
								return o1.x - o2.x;
							}
							return o1.dist - o2.dist;
						}
					});
				}

				s.x = fishList.get(0).x;
				s.y = fishList.get(0).y;
				s.dist += fishList.get(0).dist;
				s.eat++;
				if (s.eat == s.size) {
					s.eat = 0;
					s.size++;
				}
				
				map[s.x][s.y] = 0;
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
