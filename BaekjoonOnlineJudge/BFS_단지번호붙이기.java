import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_2667_단지번호붙이기 {
	static int N;
	static int[][] map;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;
	static int home_cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		visited = new boolean[N][N];
		int[] home = new int[N * N]; // 집의 수를 저장할 배열

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		int index = 0; // 집 배열 인덱스

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) { // 1을 발견하면
					home_cnt = 0; // 1 발견하면 집의 수 초기화
					home[index++] = bfs(i, j); // 그 위치에서 bfs 시작
				}
			}
		}

		int[] result = new int[index];
		System.arraycopy(home, 0, result, 0, index);
		Arrays.sort(result);

		System.out.println(index);
		for (int a : result) {
			System.out.println(a);
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static int bfs(int r, int c) {
		visited[r][c] = true;
		int nextR, nextC;

		for (int i = 0; i < 4; i++) {
			nextR = r + dir[i][0];
			nextC = c + dir[i][1];
			if (isIn(nextR, nextC) && map[nextR][nextC] == 1 && !visited[nextR][nextC]) {
				bfs(nextR, nextC);
				map[nextR][nextC] = 0;
				home_cnt++;
			}
		}

		return home_cnt + 1; // 처음에 들어왔을 때 더해주지 않았으므로 +1
	}
}
