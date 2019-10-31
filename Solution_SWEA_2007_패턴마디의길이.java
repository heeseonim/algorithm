import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_SWEA_2007_패턴마디의길이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			int count = Integer.MAX_VALUE;
			for (int i = 1; i <= 10; i++) {
				if (str.substring(0, i).equals(str.substring(i, 2 * i))) {
					count = Math.min(count, i);
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	} // end of main
} // end of class
