import java.io.*;
import java.util.*;

public class M4_03_연료게이지 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int all, answer;
	static char[] charge;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			charge = br.readLine().toCharArray();
			all = charge.length;
			answer = -1;
			binarySearch();
			sb.append(((answer + 1) * 100) / all).append('%').append('\n');
		}
		System.out.println(sb);
	}

	// 왼쪽 #, 오른쪽 _
	// 끝일 경우
	public static void binarySearch() {
		int mid = -1;
		int start = 0;
		int end = all - 1;

		while (start <= end) {
			mid = (start + end) / 2;
			if (charge[mid] == '#') {
				answer = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}
}
