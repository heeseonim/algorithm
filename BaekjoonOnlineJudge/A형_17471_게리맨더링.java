import java.io.*;
import java.util.*;

public class A형_17471_게리맨더링 {
	public static int N; // 구역 수
	public static int[] people; // 인구정보
	public static int[][] map; // 인접행렬
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 구역의 개수

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		people = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int x = Integer.parseInt(st.nextToken());
				map[i][x] = 1;
			}
		}

		answer = Integer.MAX_VALUE;
		for (int r = 1; r < N; r++) {
			boolean[] choice = new boolean[N + 1];
			comb(choice, 1, N + 1, r);
		}

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	// start : 기준, n: 최대범위, r : 뽑을 개수
	public static void comb(boolean[] choice, int start, int n, int r) {
		if (r == 0) {
			// 종료

			boolean[] visit = new boolean[N + 1];
			int cnt = 0;
			// 연결확인
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					bfs(choice, visit, i);
					cnt++;
				}
			}

			// 선거구가 2개로 나눠져있는 것을 확인했다면 인구 비교
			if (cnt == 2) {
				int one = 0;
				int two = 0;

				for (int i = 1; i <= N; i++) {
					if (choice[i])
						one += people[i];
					else
						two += people[i];
				}

				int diff = Math.abs(one - two);
				answer = answer > diff ? diff : answer;
			}

			return;
		}

		for (int i = start; i < n; i++) {
			choice[i] = true; // 사용
			comb(choice, i + 1, n, r - 1);
			choice[i] = false; // 사용 안함
		}
	}

	// 선거구 (true, false), 각 구역 방문배열, 넘겨받은 n
	public static void bfs(boolean[] choice, boolean[] visit, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(n);
		visit[n] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 연결된 구역, 같은 선거구인지 검사
			for (int i = 1; i <= N; i++) {
				if (visit[i] || choice[cur] != choice[i] || map[cur][i] != 1)
					continue;

				visit[i] = true;
				queue.add(i);
			}
		}
	}

}
