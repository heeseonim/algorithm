
/**
 * 
 * @author heeseonim
 *
 * 1. n 개의 노드
 * 2. 1번 노드에서 가장멀리 떨어진 노드의 갯수
 * 3. 가장 멀리 떨어진 노드란 : 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들
 * 
 * 1. 인접행렬로 연결 표시
 * 2. BFS로 노드 탐색
 * 3. 최단거리 배열에 저장?
 */

import java.util.*;

public class 그래프_가장먼노드 {
	public static void main(String[] args) {
		System.out.println(
				solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
	}

	public static int[] minDist; // 최단경로를 저장할 배열

	public static int solution(int n, int[][] edge) {
		boolean[][] map = new boolean[n + 1][n + 1]; // 인접행렬
		for (int[] e : edge) {
			map[e[0]][e[1]] = map[e[1]][e[0]] = true;
		}

		boolean[] visited = new boolean[n + 1];
		minDist = new int[n + 1];
		BFS(n, map, visited);

		Arrays.sort(minDist); // 오름차순 정렬
		int max = minDist[n]; // 제일 끝 값이 최댓값
		int answer = 1;
		for (int i = 1; i < n; i++) {
			if (max != minDist[n - i]) // 뒤쪽부터 순회하며 최댓값과 다르면 멈춤
				break;
			answer++;
		}
		return answer;
	}

	public static void BFS(int n, boolean[][] map, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();

		visited[1] = true;
		for (int i = 1; i < map[1].length; i++)
			if (map[1][i]) { // 1번 노드와 연결되어 있다면
				queue.add(i);
				visited[i] = true; // 방문처리
				minDist[i] = 1;
			}

		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int i = 2; i <= n; i++) {
				if (visited[i]) // 방문한 노드면 continue
					continue;
				if (map[node][i]) {// 해당 노드와 연결되어 있다면
					queue.add(i); // 새로운 노드 추가
					visited[i] = true;
					minDist[i] = minDist[node] + 1;
				}
			}
		}
	} // end of BFS

}
