import java.util.Scanner;

public class Solution_SWEA_1289_원재의메모리복구하기 {

	public static void main(String[] args) {
		int T;
		String num = "";
		int[] bit = new int[51];
		Scanner scan = new Scanner(System.in);

		T = scan.nextInt();
		for (int tc = 0; tc < T; tc++) {
			num = scan.next();

			for (int i = 0; i < num.length(); i++) {
				bit[i] = num.charAt(i) - 48;
			}

			int cnt = 0;
			for (int i = 0; i < num.length(); i++) {
				if (bit[i] == 1) {
					cnt++;
					for (int j = i + 1; j < num.length(); j++) {
						if (bit[j] == 1)
							cnt--;
						else
							cnt++;
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", tc + 1, cnt);
		}
	}
}
