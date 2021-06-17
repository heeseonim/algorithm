import java.io.*;
import java.util.*;

public class M4_02_Root계산기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static long answer;
	
	public static void main(String[] args) throws Exception {
		binarySearch(Integer.parseInt(br.readLine()));
		System.out.println(answer);
	}

	public static void binarySearch(long number) {
		long mid = 0;
		long start = 0;
		long end = number;

		while (start <= end) {
			mid = (start + end) / 2;

			if (mid * mid == number) {
				answer = mid;
				return;
			}

			if (mid * mid < number) {
				start = mid + 1;
				answer = mid; // 작을 때 저장
			} else
				end = mid - 1;
		}
	}
}
