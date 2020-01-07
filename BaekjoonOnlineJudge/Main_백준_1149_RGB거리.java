import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1149 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] house = new int[N+1][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			house[i][0] = Integer.parseInt(st.nextToken());
			house[i][1] = Integer.parseInt(st.nextToken());
			house[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			house[i][0] += Math.min(house[i-1][1], house[i-1][2]);
			house[i][1] += Math.min(house[i-1][0], house[i-1][2]);
			house[i][2] += Math.min(house[i-1][0], house[i-1][1]);
		}
		
		Arrays.sort(house[N]);
		System.out.println(house[N][0]);
	}
}
