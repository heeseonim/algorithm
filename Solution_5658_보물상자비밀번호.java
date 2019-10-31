import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_5658_보물상자비밀번호 {
	public static String str;
	public static TreeSet<Integer> ts;
	public static int num;
	public static int N;

	// swea 5658 보물상자 비밀번호
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			str = br.readLine().trim();
			num = N / 4; // 한 변에 존재하는 수의 개수

			ts = new TreeSet<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			for (int i = 0; i < num; i++) {
				rotate(i);
			}

			sb.append('#').append(tc).append(' ');
			int index = 0;
			for (int a : ts) {
				if (++index == K)
					sb.append(a);
			}
			sb.append('\n');

		}
		System.out.print(sb);

	}

	public static void rotate(int n) {
		for (int i = n; i < N; i += num) {
			if (i + num > N) {
				String s = str.substring(i, N) + str.substring(0, n);
				int end = Integer.parseInt(s, 16);
				ts.add(end);
				break;
			}

			int result = Integer.parseInt(str.substring(i, i + num), 16);
			ts.add(result);
		}
	}
}
