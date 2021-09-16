import java.io.*;
import java.util.*;

public class SegmentTree_11505_구간곱구하기 {
	public static long[] arr;
	public static long[] tree;
	public static int N, M, K;
	public static int MOD = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new long[N + 1];
		tree = new long[N * 4];
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(1, N, 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				long c = Long.parseLong(st.nextToken());
				arr[b] = c;
				update(1, N, 1, b, c);
			} else {
				int c = Integer.parseInt(st.nextToken());
				System.out.println(gob(1, N, 1, b, c));
			}
		}
	}

	public static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) >> 1;
		return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
	}

	public static long update(int start, int end, int node, int index, long diff) {
		if (index < start || index > end)
			return tree[node];

		if (start == end) // 리프 노드 업데이트
			return tree[node] = diff; // 바꾸고

		int mid = (start + end) >> 1;
		// merge
		return tree[node] = (update(start, mid, node * 2, index, diff)
				* update(mid + 1, end, node * 2 + 1, index, diff)) % MOD;
	}

	public static long gob(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return 1; // 곱!

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) >> 1;
		return (gob(start, mid, node * 2, left, right) * gob(mid + 1, end, node * 2 + 1, left, right)) % MOD;
	}

}
