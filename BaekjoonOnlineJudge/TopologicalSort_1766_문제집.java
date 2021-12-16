import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static List<Integer>[] list;
	public static int[] indegree;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		indegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[A].add(B); // A번 문제는 B번 문제보다 먼저 풀어야 함
			indegree[B]++; // 선행문제 수
		}
		
		topologySort();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void topologySort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(' ');
			
			for(int next : list[cur]) {
				if (--indegree[next] == 0)
					pq.add(next);
			}
		}
		
	}

}
