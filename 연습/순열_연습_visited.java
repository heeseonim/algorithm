package practice_0826;

import java.util.Arrays;

public class 순열연습2 {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		int[] output = new int[3];
		boolean[] visited = new boolean[3];
		perm(arr, output, visited, 0, 3, 3);
	}
	static void perm(int[] arr, int[] output, boolean[] visited,
			int depth, int n, int r) {
		if (depth == r) {
			System.out.println(Arrays.toString(output));
			return;
		}
		
		for(int i=0; i<n; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				perm(arr, output, visited, depth+1, n, r);
//				output[depth] = 0;
				visited[i] = false;
			}
		}
	}
}
