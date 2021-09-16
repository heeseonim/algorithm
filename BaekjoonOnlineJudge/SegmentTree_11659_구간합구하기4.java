import java.io.*;
import java.util.*;

public class SegmentTree_11659_구간합구하기4 {
	public static int[] arr;
	public static long[] tree;
	public static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		tree = new long[N * 4];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(sum(1, N, 1, a, b));
		}
	}

	public static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) >> 1;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}

	public static long sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) >> 1;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

}

