import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main_백준_14502_연구소 {
	static int N, M;
	static int[][] map;
	static int[][] copyMap;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copyMap = new int[N][M];
		Vector<Point> vector = new Vector<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				if (map[i][j] == 0) {
					vector.add(new Point(i, j)); // 0인 위치를 벡터에 넣어줌
				}
			}
		}
		
		int result = 0; // 안전영역 크기
		for (int i = 0; i < vector.size()-2; i++) {
			for (int j = i+1; j < vector.size()-1; j++) {
				for (int k = j+1; k < vector.size(); k++) {
					// 세개의 좌표를 뽑음
					Point one = vector.get(i);
					Point two = vector.get(j);
					Point three = vector.get(k);
					
					// 지도 복사
					for (int l = 0; l < N; l++) {
						for (int l2 = 0; l2 < M; l2++) {
							copyMap[l][l2] = map[l][l2];
						}
					}
					
					// 뽑힌 위치에 벽 세우기
					copyMap[one.x][one.y] = 1;
					copyMap[two.x][two.y] = 1;
					copyMap[three.x][three.y] = 1;
					
					// 벽 세운 후 바이러스 퍼트리기
					for (int l = 0; l < N; l++) {
						for (int l2 = 0; l2 < M; l2++) {
							if(copyMap[l][l2] == 2) {
								dfs(l, l2);
							}
						}
					}
					
					// 안전영역 개수 세기
					int cnt = 0;
					for (int l = 0; l < N; l++) {
						for (int l2 = 0; l2 < M; l2++) {
							if (copyMap[l][l2] == 0) {
								cnt++;
							}
						}
					}					
					result = (cnt > result) ? cnt : result;
					
				}
			}
		}
		System.out.println(result);

	}

	// 바이러스가 퍼지는 dfs - 2일 때 호출
	public static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) { // 상하좌우 이동
			int nextR = r + dir[i][0];
			int nextC = c + dir[i][1];
			if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M) {
				if (copyMap[nextR][nextC] == 0) {
					copyMap[nextR][nextC] = 2;
					dfs(nextR, nextC); // 이어서 바이러스 퍼트리기
				}
			}
		}
	}
}
