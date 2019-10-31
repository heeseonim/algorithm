import java.util.Scanner;

public class Solution_SWEA_2805_농작물수확하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			int result = 0;
			int num = N / 2;			
			
			for (int i = 0; i <= num; i++) {
				for (int j = num-i; j <= num+i; j++) {
					result += map[i][j];
				}
			}
			
			int cnt = map.length-1;
			for (int i = 0; i < num; i++) {
				for (int j = num-i; j <= num+i; j++) {
					result += map[cnt-i][j];
				}
			}			
			System.out.println("#" + tc + " " + result);
		}

	}
}
