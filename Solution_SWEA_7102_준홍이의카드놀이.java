import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_7102_준홍이의카드놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] count = new int[N + M + 1];
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					count[i+j]++;
					max = Math.max(max, count[i+j]);
				}
			}
			
			System.out.print("#" + tc + " ");
			for (int i = 1; i < count.length; i++) {
				if(max == count[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}
	}
}
