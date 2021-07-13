import java.io.*;
import java.util.*;

public class BOJ_2357_최솟값과최댓값 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();

	public static int[] arr, minTree, maxTree;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		minTree = new int[N * 4];
		maxTree = new int[N * 4];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		minInit(1, N, 1);
		maxInit(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(findMin(1, N, 1, a, b)).append(' ').append(findMax(1, N, 1, a, b)).append('\n');
		}
		
		bw.append(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int minInit(int start, int end, int node) {
		if (start == end)
			return minTree[node] = arr[start];

		int mid = (start + end) / 2;
		return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
	}

	public static int maxInit(int start, int end, int node) {
		if (start == end)
			return maxTree[node] = arr[start];

		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(maxInit(start, mid, node * 2), maxInit(mid + 1, end, node * 2 + 1));
	}

	public static int findMin(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return Integer.MAX_VALUE;

		if (left <= start && end <= right)
			return minTree[node];

		int mid = (start + end) / 2;
		return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
	}

	public static int findMax(int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return Integer.MIN_VALUE;

		if (left <= start && end <= right)
			return maxTree[node];

		int mid = (start + end) / 2;
		return Math.max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right));
	}

}
