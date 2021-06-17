import java.io.*;
import java.util.*;

public class M2_06_UglyNumber {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long[] arr = new long[1501]; //1500 번째 까지 기록
		
		pq.add((long)1);
		int cnt = 1;
		long now = -1;
		
		while(cnt < arr.length) {
			long cur = pq.poll();
			if (now == cur) // 중복방지
				continue;
			arr[cnt++] = cur;
			
			pq.add(cur * 2);
			pq.add(cur * 3);
			pq.add(cur * 5);
			
			now = cur; // 중복방지
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int th = Integer.parseInt(st.nextToken());
			sb.append(arr[th]).append(' ');
		}
		
		System.out.println(sb);
	}
}
