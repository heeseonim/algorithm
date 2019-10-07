import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS {
	static int N, M, V;
	static boolean[] visited;
	static int[][] map;

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
		V = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1]; // 1~N 사용
		visited = new boolean[N + 1]; // 1~N 사용
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st2.nextToken());
			int y = Integer.parseInt(st2.nextToken());
			map[x][y] = 1;
			map[y][x] = 1;
		}

		dfs(V);
		Arrays.fill(visited, false);
		System.out.println();
		bfs(V);
		

	}
	
	static void bfs(int i) { // 큐를 통해 탐색
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		visited[i] = true;
		System.out.print(i + " "); // 방문 정점 출력
		
		while(!queue.isEmpty()) {
			int nextI = queue.poll();
			for (int j = 1; j <= N; j++) {
				if (map[nextI][j] == 1 && !visited[j]) { //현재 정점과 연결되어있고, 그 지점을 방문하지 않았을 때
					queue.add(j); // 해당 지점을 큐에 넣어주고
					visited[j] = true; // 방문처리
					System.out.print(j + " "); // 방문 정점 출력
				}
			}
		}
		
	}
	
	static void dfs(int i) { // 지점에서 이어서 깊이 탐색
		visited[i] = true;
		System.out.print(i + " ");
		
		for (int j = 1; j <= N; j++) {
			if(map[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}
	}

}
