import java.io.*;
import java.util.*;

// Segment Tree
// Fenwick Tree - point update, range query
public class Main {
	public static int N, M;
	public static long[] arr;
	public static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[N + 1];
		for (int tc = 0; tc < M; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int comm = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			if (comm == 0) { // sum
				int j = Integer.parseInt(st.nextToken());
				if (i > j) { // i 가 더 클 경우
					int temp = i;
					i = j;
					j = temp;
				}
				System.out.println(sum(j) - sum(i - 1));
			} else { // modify
				long k = Long.parseLong(st.nextToken());
				modify(i, k - arr[i]); // 차이값을 전달하여 업데이트
				arr[i] = k; // 배열값 변경
			}
		}

	}

	public static void modify(int i, long num) {
		while (i <= N) {
			tree[i] += num;
			i += (i & -i);
		}
	}

	public static long sum(int i) {
		long result = 0;
		while (i > 0) { // i 는 1까지 있음
			result += tree[i];
			i -= (i & -i);
		}
		return result;
	}
}
