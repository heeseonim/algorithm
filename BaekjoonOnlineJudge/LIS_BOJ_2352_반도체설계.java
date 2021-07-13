import java.io.*;
import java.util.*;

public class BOJ_2352_반도체설계 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] count = new int[N];
		int[] path = new int[N];
		int size = 0;
		path[size] = -1;
		count[size++] = 0;

		for (int i = 1; i < N; i++) {
			if (arr[count[size - 1]] < arr[i]) {
				path[i] = count[size - 1];
				count[size++] = i;
			} else {
				int idx = binarySearch(arr, count, 0, size, arr[i]);
				if (idx < 0) {
					idx = -idx - 1;
					path[i] = path[count[idx]];
					count[idx] = i;
				}
			}
		}
		
		System.out.println(size);
	}

	public static int binarySearch(int[] arr, int[] count, int start, int end, int key) {
		int low = start;
		int high = end - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[count[mid]] < key)
				low = mid + 1;
			else if (arr[count[mid]] > key)
				high = mid - 1;
			else
				return mid;
		}

		return -(low + 1);
	}
}
