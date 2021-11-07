import java.io.*;
import java.util.*;

public class Main {
	public static long K, N, answer;
	public static long[] data;
	
	public static void main(String[] args) throws Exception {
		// 이분탐색
		// 1 ~ max 값 사이에서 분리 시 N개를 만족하는 최대 길이 찾기
		// 만들 수 없는 경우는 없음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		long max = 0;
		data = new long[(int) K];
		for (int i = 0; i < K; i++) {
			data[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, data[i]);
		}
		
		answer = 0;
		binarySearch(1, max);
		System.out.println(answer);
	}
	
	public static long separate(long mid) {
		long cnt = 0;
		
		for (int i = 0; i < K; i++) { // 분리했을 때 개수 계산
			cnt += data[i] / mid;
		}
		
		return cnt;
	}
	
	public static void binarySearch(long start, long end) {
		long left = start;
		long right = end;
		
		while(left <= right) {
			long mid = (left + right) >> 1;
			long result = separate(mid);
			if (result >= N) { // 잘려진 길이가 많음 (작게 자름)
				left = mid + 1; // 최대 길이 계속 탐색
				answer = Math.max(answer, mid); // ** N개보다 많이 만드는 것도 N개를 만드는 것에 포함
			} else { // 잘려진 길이가 적음 (크게 자름)
				right = mid - 1;
			}
		}
	}
}
