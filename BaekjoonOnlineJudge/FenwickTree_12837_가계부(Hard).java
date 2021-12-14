import java.io.*;
import java.util.*;

public class Main {
	public static int N, Q;
	public static long[] arr;
	public static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[N + 1];
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int comm = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if (comm == 1) {
				long x = Long.parseLong(st.nextToken());
				update(p, x); // dif(차이값)가 아니라, x를 추가한다.
				arr[p] += x;
			} else {
				int q = Integer.parseInt(st.nextToken());
				sb.append(sum(q) - sum(p-1)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void update(int i, long val) {
		while (i <= N) {
			tree[i] += val;
			i += (i & -i);
		}
	}
	
	public static long sum(int i) {
		long result = 0l;
		while(i > 0) {
			result += tree[i];
			i -= (i & -i);
		}
		return result;
	}
}
