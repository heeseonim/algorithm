import java.io.*;
import java.util.*;

public class Main {
	public static int N, M;
	public static int[] arr;
	public static int[] tree;

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new int[N * 4];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init(1, N, 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int comm = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			if (comm == 1) {
				int v = Integer.parseInt(st.nextToken());
				arr[index] = v; // 바꾼다.
				update(1, N, 1, index);
			} else {
				int j = Integer.parseInt(st.nextToken());
				sb.append(find(1, N, 1, index, j)).append('\n');
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) >> 1;
		return tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
	}

	public static int update(int start, int end, int node, int index) {
		if (index > end || index < start)
			return tree[node]; // 현재 노드 리턴

		if (start == end) // 리프노드 업데이트
			return tree[node] = arr[index];

		// 수정된 값으로 트리 재구성
		int mid = (start + end) >> 1;
		return tree[node] = Math.min(update(start, mid, node * 2, index), update(mid + 1, end, node * 2 + 1, index));
	}

	public static int find(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return Integer.MAX_VALUE; // 해당 값이 리턴되지 않도록 최댓값 리턴

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) >> 1;
		return Math.min(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
	}

}
