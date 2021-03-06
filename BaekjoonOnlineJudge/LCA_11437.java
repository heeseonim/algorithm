import java.io.*;
import java.util.*;

public class LCA2_11438 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		LinkedList<Integer>[] list = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			// 각 노드마다 리스트 생성
			list[i] = new LinkedList<Integer>();
		}

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 간선연결
			list[x].add(y);
			list[y].add(x);
		}

		int[] parent = new int[N + 1];
		int[] depth = new int[N + 1];

		Queue<Integer> queue = new LinkedList<Integer>();
		int START = 1;
		queue.add(START);
		parent[START] = START;
		depth[START] = START;

		while (!queue.isEmpty()) {
			int from = queue.poll();

			// from 노드에 연결된 노드들 확인
			for (int to : list[from]) {
				if (parent[to] == 0) { // 부모정보가 없다면 부모정보, 깊이정보 입력
					parent[to] = from;
					depth[to] = depth[from] + 1;
					queue.add(to); // 해당 지점 큐에 삽입하여 연이어 탐색
				}
			}
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (depth[x] > depth[y]) { // 깊이가 깊은 쪽을 y로 바꿔줌
				int temp = x;
				x = y;
				y = temp;
			}

			while (depth[x] != depth[y]) { // 깊이가 다르다면, 깊이를 맞춰줌
				y = parent[y];
			}

			if (x == y) { // 같아졌다면, 공통 조상 발견
				System.out.println(x);
			} else { // 다르다면, 같을 때까지 부모 찾기
				while (parent[x] != parent[y]) {
					x = parent[x];
					y = parent[y];
				}

				System.out.println(parent[x]);
			}
		}
	}
}
