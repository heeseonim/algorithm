import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static int[] arr, LIS;
	
	public static void main(String[] args) throws Exception {
		// LIS
		// 작은 거 발견됐을 때 lower bound 실행
		// 발견해서 변경한 횟수가 최소횟수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		LIS = new int[N];
		int size = 0; // LIS 사이즈
		int cnt = 0; // 변경횟수
		for (int i = 0; i < N; i++) {
			if (LIS[size] < arr[i]) {
				LIS[++size] = arr[i];
			} else {
				int index = lowerBound(0, size, arr[i]);
				LIS[index] = arr[i];
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	public static int lowerBound(int start, int end, int target) {
		int left = start;
		int right = end;
		while(left < right) {
			int mid = (left + right) >> 1;
			if (LIS[mid] < target) { // 작으면 오른쪽 범위 보기
				left = mid + 1;
			} else { // 같거나 크면 그 위치 저장
				right = mid;
			}
		}
		
		return right; // 같거나 큰 위치 반환
	}
}
