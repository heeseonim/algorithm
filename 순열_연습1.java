package practice_0826;

import java.util.Arrays;

public class 순열연습1 {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		permutation(arr, 0, 3, 3);
	}

	static void permutation(int[] arr, int depth, int n, int r) {
		if (depth == r) {
			System.out.println(Arrays.toString(arr));
			return;
		}

		for (int i = depth; i < n; i++) {
			swap(arr, depth, i);
			permutation(arr, depth + 1, n, r);
			swap(arr, depth, i);
		}
	}

	static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}
}
