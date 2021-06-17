import java.io.*;
import java.util.*;

public class M2_03_SortLibrary {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());

		Integer[] arr = new Integer[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		List<type> comArr = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			comArr.add(new type(arr[i], st.nextToken()));
		}

		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}

		sb.append('\n');

		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(' ');
		}

		sb.append('\n');

		Collections.sort(comArr);
		for (int i = 0; i < N; i++) {
			sb.append(comArr.get(i).num).append(' ');
		}
		
		sb.append('\n');
		
		for (int i = 0; i < N; i++) {
			sb.append(comArr.get(i).alpha).append(' ');
		}

		System.out.println(sb);
	}

	public static class type implements Comparable<type> {
		int num;
		String alpha;

		public type(int num, String alpha) {
			this.num = num;
			this.alpha = alpha;
		}

		@Override
		public int compareTo(type o) {
			// 1. 짝수 우선
			int modThis = this.num % 2;
			int modO = o.num % 2;
			if (modThis == 0 && modO != 0)
				return -1;
			if (modThis != 0 && modO == 0)
				return 1;
			if (modThis == 0 && modO == 0) {
				if (this.num == o.num) {
					return this.alpha.compareTo(o.alpha);
				}
				return this.num - o.num;
			}

			if (this.num == o.num) {
				return this.alpha.compareTo(o.alpha); // 3. 문자 오름차순
			}
			return this.num - o.num; // 2. 숫자 오름차순
		}
	}
}
