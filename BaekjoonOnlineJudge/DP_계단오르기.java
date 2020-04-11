import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2579 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] step = new int[N+1]; // 계단의 각 점수
		for (int i = 1; i <= N; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}
		
		int[] max = new int[N+1]; // 최댓값 저장
		max[1] = step[1];
		max[2] = step[2] + max[1];
		
		// max[N] = step[N] + step[N-1] + max[N-3]
		// max[N] = step[N] + max[N-2]
		for (int i = 3; i < max.length; i++) {
			max[i] = Math.max(step[i] + step[i-1] + max[i-3], step[i] + max[i-2]);
		}
		
		System.out.println(max[N]);		
	}
}
