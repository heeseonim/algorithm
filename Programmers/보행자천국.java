
public class 보행자천국 {

	int MOD = 20170805;

	public int solution(int m, int n, int[][] cityMap) {
		int[][] right = new int[m + 1][n + 1];
		int[][] down = new int[m + 1][n + 1];

		right[1][1] = down[1][1] = 1;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				
				if (i == 1 && j == 1)
					continue;

				int prev = cityMap[i - 1][j - 1];
				switch (prev) {
				case 0:
					right[i][j] = (right[i][j-1] + down[i-1][j]) % MOD;
					down[i][j] = (right[i][j-1] + down[i-1][j]) % MOD;
					break;
				case 1:
					right[i][j] = 0;
					down[i][j] = 0;
					break;
				case 2:
					right[i][j] = right[i][j-1];
					down[i][j] = down[i-1][j];
					break;
				}
			}
		}

		return right[m][n] % MOD;
	}
	
	public static void main(String[] args) {
		보행자천국 보 = new 보행자천국();
		System.out.println(
				보.solution(3, 6, new int[][] { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }));
	}
}
