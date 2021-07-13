import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10999_구간합구하기2 {
	public static int[] arr;
	public static long[] tree;
	public static long[] lazy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 변경 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간 합 구하는 횟수

		arr = new int[N + 1];
		lazy = new long[N * 4];
		tree = new long[N * 4];
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		init(1, N, 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			if (type == 1) {
				long dif = Long.parseLong(st.nextToken());
				update(1, N, 1, left, right, dif);
			} else {
				System.out.println(query(1, N, 1, left, right));
			}
		}
	}

	public static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];
		else {
			int mid = (start + end) / 2;
			return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		}
	}

	public static void update(int start, int end, int node, int left, int right, long dif) {

		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node]; //

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}

		if (left > end || right < start)
			return;

		if (left <= start && end <= right) {
			tree[node] += (end - start + 1) * dif;

			if (start != end) {
				lazy[node * 2] += dif;
				lazy[node * 2 + 1] += dif;
			}
			return; //
		}

		int mid = (start + end) / 2;
		update(start, mid, node * 2, left, right, dif);
		update(mid + 1, end, node * 2 + 1, left, right, dif);

		tree[node] = tree[node * 2] + tree[node * 2 + 1]; // merge
	}

	public static long query(int start, int end, int node, int left, int right) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node]; //

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
		
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
	}
}
