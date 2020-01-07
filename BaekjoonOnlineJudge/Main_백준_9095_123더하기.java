import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9095 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] num = new int[11];
		num[1] = 1; // 1
		num[2] = 2; // 1+1 2
		num[3] = 4; // 1+1+1 1+2 2+1 3
		// num[n] = num[n-1] + num[n-2] + num[n-3]
		
		for (int i = 4; i < num.length; i++) {
			num[i] = num[i-1] + num[i-2] + num[i-3];
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			System.out.println(num[Integer.parseInt(br.readLine())]);
		}
	}
}
