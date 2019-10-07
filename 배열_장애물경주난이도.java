import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6730_장애물경주난이도 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] block = new int[N];
			for (int i = 0; i < N; i++) {
				block[i] = Integer.parseInt(st.nextToken());
			}
			int upMax = 0;
			int downMax = 0;
			for (int i = 0; i < block.length-1; i++) {
				if (block[i] < block[i+1]) { // 올라갈 때
					upMax = Math.max(upMax, block[i+1]-block[i]);
				} else { // 내려갈 때
					downMax = Math.max(downMax, block[i]-block[i+1]);
				}
			}
			System.out.println("#" + tc + " " + upMax + " " + downMax);
		}
	}
}
