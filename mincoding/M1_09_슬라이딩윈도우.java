import java.io.*;
import java.util.*;

public class M1_09_슬라이딩윈도우 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int sum = 0; // 첫 구간의 합
			for (int i = 0; i < W; i++) {
				sum += arr[i];
			}
			
			int max = sum;
			int maxIndex = 0;

			for (int start = 1; start + W - 1 < N; start++) {
				sum -= arr[start - 1]; // 제거 구간
				sum += arr[start + W - 1]; // 추가 구간
				if (max < sum) {
					max = sum;
					maxIndex = start;
				}
			}

			sb.append('#').append(tc).append(' ').append(maxIndex).append(' ').append(maxIndex + W - 1).append(' ')
					.append(max).append('\n');
		}
		System.out.println(sb);
	}

}
