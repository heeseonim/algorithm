import java.io.*;
import java.util.*;

public class BinarySearch_1920_수찾기 {
	public static int N, M;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (binarySearch(0, N, num) < 0)
				System.out.println(0);
			else
				System.out.println(1);
		}
	}

	public static int binarySearch(int left, int right, int key) {
		int low = left;
		int high = right - 1;

		while (low <= high) {
			int mid = (low + high) >> 1;
			if (arr[mid] > key)
				high = mid - 1;
			else if (arr[mid] < key)
				low = mid + 1;
			else
				return mid;
		}
		return -1;
	}
}
