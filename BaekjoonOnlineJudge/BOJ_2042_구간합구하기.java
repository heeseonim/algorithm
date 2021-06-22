import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2042_구간합구하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long[] arr, tree;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

		arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		tree = new long[N * 4];

		init(1, N, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				long dif = c - arr[b];
				arr[b] = c;
				update(1, N, 1, b, dif);
			} else
				sb.append(sum(1, N, 1, b, (int) c)).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) / 2;

		// 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로 함
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}

	public static void update(int start, int end, int node, int idx, long dif) {
		if (idx < start || idx > end)
			return;

		// 범위 안에 있으면 내려가며 다른 원소도 갱신
		tree[node] += dif;
		if (start == end)
			return;

		int mid = (start + end) / 2;
		update(start, mid, node * 2, idx, dif);
		update(mid + 1, end, node * 2 + 1, idx, dif);
	}

	public static long sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return 0;

		// 범위 안에 있는 경우
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}
