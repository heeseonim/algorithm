import java.io.*;
import java.util.*;

public class M4_01_BinarySearch {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); // 정렬된 상태에서 시작해야 함

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			if (binarySearch(target))
				sb.append('O');
			else
				sb.append('X');
		}
		
		System.out.println(sb);
	}

	public static boolean binarySearch(int target) {
		int start = 0;
		int end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (arr[mid] == target)
				return true;

			if (arr[mid] < target) // target 이 더 크면 오른쪽으로
				start = mid + 1;
			else
				end = mid - 1;
		}
		
		return false;
	}
}
