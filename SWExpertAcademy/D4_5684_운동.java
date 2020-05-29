import java.io.*;
import java.util.*;

public class D4_5684_운동 {
	public static int N, M;
	public static int[][] map;
	public static boolean[] visit;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer
						.parseInt(st.nextToken());
			}

			answer = Integer.MAX_VALUE;
			// 각 건물에서 출발해보기
			for (int now = 0; now < N; now++) {
				visit = new boolean[N];
				dfs(now, now, 0);
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb.toString());
		}
	}

	// 현재 위치, 시작한 지점, 총 거리의 합
	public static void dfs(int now, int start, int dist) {
		if (now == start && visit[now]) { // 현재 위치가 시작점과 같고, 현재 위치를 이미 방문했을 때
			answer = answer > dist ? dist : answer;
			return;
		}
		
		if (visit[now]) // 시작점 아닌데 사이클 발생했다면 return
			return;
		
		if (dist > answer) // dist가 갖고있는 answer 보다 크다면 더 탐색할 필요 없으므로 return
			return;

		visit[now] = true; // 현재 위치 방문 처리
		for (int i = 0; i < N; i++)
			if (map[now][i] > 0) // 도로가 연결되어 있으면 이동
				dfs(i, start, dist + map[now][i]);
	}
}
