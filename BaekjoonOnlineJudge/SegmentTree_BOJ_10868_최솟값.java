import java.io.*;
import java.util.*;

public class BOJ_10868_최솟값 {
	static long[] tree;
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new long[N + 1];
		tree = new long[N * 4];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		initMin(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			System.out.println(findMin(1, N, 1, left, right));
		}
	}

	static long initMin(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) / 2;
		return tree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
	}

	static long findMin(int start, int end, int node, int left, int right) {
		if (right < start || left > end) {
			return Long.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
	}
}
