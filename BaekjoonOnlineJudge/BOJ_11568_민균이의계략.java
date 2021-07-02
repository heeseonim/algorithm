import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11568_민균이의계략 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		int[] LIS = new int[N];
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			LIS[i] = 1;			
			for (int j = 0; j < i; j++) { // 앞 원소 체크
				if (arr[j] < arr[i] && LIS[j] + 1 > LIS[i]) { // 증가하는 모습이 보이도록
					LIS[i] = LIS[j] + 1;
				}
			}
		}
		
		int maxIndex = 0;
		for (int i = 0; i < N; i++) {
			if (LIS[maxIndex] < LIS[i])
				maxIndex = i;
		}
		
		sb.append(LIS[maxIndex]);
		
		bw.append(sb.toString());
		bw.flush();
	}
}
