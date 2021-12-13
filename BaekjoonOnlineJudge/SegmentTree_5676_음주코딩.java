import java.io.*;
import java.util.*;

public class Main {
	public static int[] map;
	public static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String temp = "";
		while ((temp = br.readLine()) != null) { // 들어오지 않을 때까지 while 문 실행
			if (temp.isEmpty()) break;
			StringTokenizer st = new StringTokenizer(temp, " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N + 1]; // 결과가 +, -, 0 으로 나오면 되기 때문에 1,-1,0을 저장해준다
			tree = new int[N * 4];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				map[i] = invert(Integer.parseInt(st.nextToken()));
			}

			init(1, N, 1);
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String comm = st.nextToken();
				if ("C".equals(comm)) { // 변경
					int index = Integer.parseInt(st.nextToken());
					int V = Integer.parseInt(st.nextToken());
					change(1, N, 1, index, invert(V));
				} else { // 곱셈
					int from = Integer.parseInt(st.nextToken());
					int to = Integer.parseInt(st.nextToken());
					int result = gob(1, N, 1, from, to);
					
					if (result > 0)
						sb.append('+');
					else if (result < 0) {
						sb.append('-');
					} else {
						sb.append('0');
					}
				}
			}
			sb.append('\n');
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int init(int start, int end, int node) {
		if (start == end)
			return tree[node] = map[start];

		int mid = (start + end) >> 1;
		return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1);
	}

	public static int change(int start, int end, int node, int index, int dif) {
		if (index > end || index < start)
			return tree[node];

		if (start == end)
			return tree[node] = dif; // 지점 변경 (point update)

		// merge
		int mid = (start + end) >> 1;
		return tree[node] = change(start, mid, node * 2, index, dif) * change(mid + 1, end, node * 2 + 1, index, dif);
	}

	public static int gob(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1; // 곱셈 항등원
		}

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) >> 1;
		return gob(start, mid, node * 2, left, right) * gob(mid + 1, end, node * 2 + 1, left, right);
	}

	public static int invert(int num) {
		if (num > 0)
			return 1;
		else if (num < 0)
			return -1;
		else
			return 0;
	}
}
