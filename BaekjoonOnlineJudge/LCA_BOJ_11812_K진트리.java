import java.io.*;
import java.util.*;

public class BOJ_11812_K진트리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			if (K == 1) // K가 1이면 한줄로 배열되므로 차이만 구해줌
				System.out.println(Math.abs(x - y));
			else {
				long dist = 0;
				while (x != y) {
					long max = Math.max(x, y);
					y = Math.min(x, y);
					x = (max - 2) / K + 1; // 더 아래에 있는 값을 위로 올림
					dist++;
				}
				System.out.println(dist);
			}			
		}
	}
}
