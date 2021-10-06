import java.io.*;
import java.util.*;

public class TwoPointer_1806_부분합 {
	public static int N, S, sum, length;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N + 1]; // +1 안해주면 아웃 인덱스 에러

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		sum = arr[0];
		length = Integer.MAX_VALUE;
		twoPointer(0, 0);
		System.out.println(length == Integer.MAX_VALUE ? 0 : length);
	}

	public static void twoPointer(int low, int high) {
		int start = low;
		int end = high;
		
		while (start <= end && end < N) { // 같거나 크고, N 보다 작고
			if (sum < S) { // S보다 작을 경우 end 를 오른쪽으로 이동
				sum += arr[++end];
			} else if (sum == S) { // 같을 경우 갱신한 후, end 를 오른쪽으로 이동
				length = Math.min(length, end - start + 1);
				sum += arr[++end];
			} else { // S보다 클 경우start 를 오른쪽으로 이동
				length = Math.min(length, end - start + 1);
				sum -= arr[start++];
			}
		}
	}
}
