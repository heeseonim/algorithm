import java.io.*;
import java.util.*;

// bfs
public class Main {
	public static int K, V, E;
	public static List<Integer>[] adjList;
	public static int[] color;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= K; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
				adjList[v].add(u); // 양방향
			}

			color = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				if (color[i] == 0) // 컬러 세팅 안된 지점만
					bfs(i); // 연결이 안된 지점도 있으니 다 보내봐야 한다.
			}
			if (check())
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		color[start] = 1; // 초기 컬러 설정

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int next : adjList[cur]) {
				if (color[next] > 0)
					continue;
				color[next] = 3 - color[cur]; // 1->2, 2->1
				q.add(next);
			}
		}
	}

	// 연결관계에 있을 때, 색깔이 다른지 확인
	public static boolean check() {
		for (int i = 1; i <= V; i++) {
			for (int next : adjList[i]) {
				if (color[next] == color[i]) // 같으면 false
					return false;
			}
		}
		return true;
	}
}
