import java.io.*;
import java.util.*;

// 참고 : https://kbw1101.tistory.com/29
public class Main {
	public static int N, k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		// 마지막 값 k : B[k] 의 값은 k보다 작거나 같다.
		bw.write(String.valueOf(binarySearch(1, k)));
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int binarySearch(int start, int end) {
		int left = start;
		int right = end;
		int result = 0;
		
		while(left <= right) {
			int mid = (left + right) >> 1;
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				// 찾아야 하는 수보다 작거나 같은 숫자의 개수
				cnt += Math.min(mid / i, N);
			}
			if (cnt >= k) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1; // 큰 쪽으로 탐색
			}
		}
		
		return result;
	}

}
