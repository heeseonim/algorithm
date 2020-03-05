import java.util.Scanner;

public class 운동2 {
	static int[][] graph; // 방향성 있는 가중치 그래프를 인접 행렬을 써보자.
	static int ans;
	static boolean[] visit;
	static int N, M; // 정점 갯수, 간선 갯수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			ans = Integer.MAX_VALUE;
			N = sc.nextInt();
			M = sc.nextInt();

			graph = new int[N + 1][N + 1]; // 1~N까지 정점번호 그대로 쓸 예정
			for (int m = 0; m < M; m++) {
				int s = sc.nextInt(); // start
				int e = sc.nextInt(); // end
				int c = sc.nextInt(); // cost
				graph[s][e] = c;
			}

			for (int now = 1; now <= N; now++) {
				visit = new boolean[N + 1]; // 출발할 때 이전의 방문 정보는 초기화 해버리자!
				dfs(now, now, 0); // now 에서 출발!
			}

			System.out.println("#" + tc + " " + (ans == Integer.MAX_VALUE ? -1 : ans));
		}
	}

	static void dfs(int now, int start, int dist) { // 현재 서있는 정점, 출발지 정점 어디였는지,
		if (now == start && visit[now]) { // 출발점으로 돌아왔녜? 사이클이다~
			if (dist < ans)
				ans = dist;
			return;
		}
		
		if (visit[now]) { // 출발점에 대한 재방문이면 위의 조건문이 걸렸을 거지만, 출발이 아닌 정점의 재방문은 지금 나한테는 관심없는 사이클
			return;
		}
		
		if (dist >= ans) // 최소 사이클을 찾고 싶은 건데 이미 이전에 찾은 사이클보다 넘 많이 걸었어... 이 길은 안가봐도 되겠다
			return;
		
		visit[now] = true;
		for (int i = 1; i <= N; i++) {
			if (graph[now][i] > 0) { // 현재 정점에서 이동 가능한 다음 next 정점 찾는 작업
				dfs(i, start, dist + graph[now][i]);
			}
		}
	}
}
