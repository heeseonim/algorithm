import java.io.*;
import java.util.*;

public class BOJ_2056_작업 {
	static int N;
	static int[] time;
	static int[] indegree;
	static List<List<Integer>> array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		time = new int[N + 1];
		indegree = new int[N + 1];
		array = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++)
			array.add(new ArrayList<Integer>());

		StringTokenizer st;
		for (int i = 1; i <= N; i++) { // 순서대로 노드번호
			st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken()); // 걸리는 시간
			int cnt = Integer.parseInt(st.nextToken()); // 선행노드 개수
			indegree[i] += cnt;
			for (int j = 0; j < cnt; j++) { // 선행노드 번호
				int s = Integer.parseInt(st.nextToken());
				array.get(s).add(i);
			}
		}

		System.out.println(topologicalSort());
	}

	public static int topologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>(); // indegree 0 담을 큐
		int[] result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			result[i] = time[i];
			if (indegree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer e : array.get(cur)) {
				// 모든 작업이 끝나지 않으면 다음 작업을 수행할 수 없기 때문에, 최대시간을 구해준다.
				result[e] = Math.max(result[e], result[cur] + time[e]);
				
				indegree[e]--;

				if (indegree[e] == 0)
					q.add(e);
			}
		}
		
		// 저장한 시간 중에서 최대 시간 찾기
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = result[i] > answer ? result[i] : answer;
		}
		return answer;
	}
}
