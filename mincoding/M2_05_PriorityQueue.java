import java.io.*;
import java.util.*;

public class M2_05_PriorityQueue {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String comm = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			
			if (comm.equals("push")) {
				pq.add(num);
			} else if (comm.equals("pop")) {
				for (int j = 0; j < num; j++) {
					sb.append(pq.poll()).append(' ');
				}
				sb.append('\n');
			} else { // add
				int cur = pq.poll();
				pq.add(cur + num);
			}
		}
		System.out.println(sb);
	}
}
