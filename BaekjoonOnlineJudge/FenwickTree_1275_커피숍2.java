import java.io.*;
import java.util.*;

public class FenwickTree_1275_커피숍2 {
	public static int N;
	public static long[] arr;
	public static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			long num = Long.parseLong(st.nextToken());
			arr[i] = num;
			update(i, num);
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			if (x > y) {
				int temp = x;
				x = y;
				y = temp;
			}

			System.out.println(sum2(x, y));
			update(a, (b - arr[a]));
			arr[a] = b; // 배열의 값도 변경해줘야함
		}
	}

	// 오른쪽으로
	public static void update(int i, long num) {
		while (i <= N) {
			tree[i] += num;
			i += (i & -i);
		}
	}

	// 왼쪽으로
	public static long sum(int i) {
		long res = 0l;

		while (i > 0) {
			res += tree[i];
			i -= (i & -i);
		}

		return res;
	}

	// 두 지점일 경우 sum 이용해서 빼주기
	public static long sum2(int start, int end) {
		return sum(end) - sum(start - 1);
	}
}
