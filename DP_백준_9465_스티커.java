import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][n + 1];
			int[][] sum = new int[2][n + 1];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j < n + 1; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sum[0][1] = arr[0][1];
			sum[1][1] = arr[1][1];
			for (int i = 2; i < n+1; i++) {
				sum[0][i] = arr[0][i] + Math.max(sum[1][i-2], sum[1][i-1]);
				sum[1][i] = arr[1][i] + Math.max(sum[0][i-2], sum[0][i-1]);
			}
			int result = Math.max(sum[0][n], sum[1][n]);
			System.out.println(result);			
		}
	}
}
