import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954_달팽이숫자 {
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			int x = 0;
			int y = -1;
			int number = 1;
			
			int bit = -1;
			int n = N;
			while(n!=0) {
				bit *= -1;
				// 우, 상
				for (int i=0; i<n; i++) {
					y = y + bit;
					map[x][y] = number++;
				}				
				n--;
				// 하, 좌
				for(int i=0; i<n; i++) {
					x = x + bit;
					map[x][y] = number++;
					
				}
			}
			
			System.out.println("#" + tc);
			for(int[] a : map) {
				for(int b : a) {
					System.out.print(b + " ");
				}
				System.out.println();
			}
			
			
		}

	}

}
