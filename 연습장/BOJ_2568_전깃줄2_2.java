import java.io.*;
import java.util.*;

public class BOJ_2568_전깃줄2_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Handle[] arr = new Handle[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			arr[i] = new Handle(left, right);
		}

		Arrays.sort(arr);
		int[] count = new int[N]; // 인덱스 저장
		int[] path = new int[N]; // 경로 저장
		int size = 0;
		path[size] = -1; // 루트 표시
		count[size++] = 0;

		for (int i = 1; i < arr.length; i++) {
			Handle cur = arr[i];
			int value = cur.right;
			if (arr[count[size - 1]].right < value) { // 마지막으로 저장된 값보다 크다면 붙이기
				path[i] = count[size - 1]; // 마지막 숫자의 index 를 나의 부모로 저장
				count[size++] = i;
			} else { // 작다면 binarySearch 실행
				int index = binarySearch(arr, count, 0, size, value);
				if (index < 0) {
					index = -index - 1;
					path[i] = path[count[index]];
					count[index] = i;
				}
			}
		}

		System.out.println(N - size);

		boolean[] visited = new boolean[N];
		for (int i = count[size - 1]; i != -1; i = path[i]) { // 경로 따라가기
			visited[i] = true;
		}
		
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				System.out.println(arr[i].left);
			}
		}
	}

	public static class Handle implements Comparable<Handle> {
		int left, right;

		public Handle(int left, int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Handle o) {
			return this.left - o.left;
		}
	}

	public static int binarySearch(Handle[] arr, int[] count, int start, int end, int key) {
		int low = start;
		int high = end - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[count[mid]].right < key)
				low = mid + 1;
			else if (arr[count[mid]].right > key)
				high = mid - 1;
			else
				return mid;
		}

		return -(low + 1);
	}
}
