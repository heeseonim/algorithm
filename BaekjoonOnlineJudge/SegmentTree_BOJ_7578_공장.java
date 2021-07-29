import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SegmentTree_BOJ_7578_공장 {
	public static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		tree = new long[N * 4];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		HashMap<Integer, Integer> hm = new HashMap<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			hm.put(Integer.parseInt(st.nextToken()), i);
		}

		long answer = 0;
		for (int i = 1; i <= N; i++) {
			update(1, N, 1, hm.get(arr[i]));
			answer += sum(1, N, 1, hm.get(arr[i]) + 1, N);
		}
		
		System.out.println(answer);
	}

	public static void update(int start, int end, int node, int index) {
		if (index > end || index < start)
			return;

		tree[node]++;

		if (start != end) {
			int mid = (start + end) / 2;
			update(start, mid, node * 2, index);
			update(mid + 1, end, node * 2 + 1, index);
		}
	}

	public static long sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}
