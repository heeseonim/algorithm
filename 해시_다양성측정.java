import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution_7728_다양성측정 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String num = br.readLine();
			HashSet<Integer> hs = new HashSet<>();
			for (int i = 0; i < num.length(); i++) {
				hs.add(num.charAt(i)-'0');
			}
			System.out.println("#" + tc + " " + hs.size());
		}
	}
}
