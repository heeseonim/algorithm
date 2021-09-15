import java.io.*;
import java.util.*;

public class SegmentTree_BinarySearch_2243_사탕상자 {
	public static long[] tree;
	public static int n;
	public static final int E = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tree = new long[E * 4]; // 사탕 : 1~1,000,000
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (A == 1) {
				int candy = binarySearch(1, E, B); // 해당 순위의 사탕맛 찾기
				System.out.println(candy);
				update(1, E, 1, candy, -1); // 빼준 뒤 업데이트
			} else {
				int C = Integer.parseInt(st.nextToken());
				update(1, E, 1, B, C);
			}
		}

	}

	// 해당 순위의 사탕맛 찾기
	public static int binarySearch(int start, int end, long rank) {
		int left = start;
		int right = end;

		while (left <= right) {
			int mid = (left + right) >> 1;
			long sum = sum(1, E, 1, 1, mid); // 1~mid까지의 합을 구함

			// 합을 기준으로 이분탐색
			if (sum < rank) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}		

		return left; // ㅜ.ㅜ
	}

	public static void update(int start, int end, int node, int index, int diff) {
		if (index < start || index > end)
			return;

		tree[node] += diff;

		if (start != end) {
			int mid = (start + end) >> 1;
			update(start, mid, node * 2, index, diff);
			update(mid + 1, end, node * 2 + 1, index, diff);
		}
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

