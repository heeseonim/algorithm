import java.util.Scanner;

public class Solution_4796_의석이의우뚝선산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] h = new int[N];
			for (int i = 0; i < N; i++) {
				h[i] = sc.nextInt();
			}

			int result = 0;
			int L = 0, R = 0;
			for (int i = 1; i < h.length; i++) {

				if (R > 0 && L == 0 && h[i] > h[i - 1]) // 감소만 하다가 증가하게 되었을 때 R 리셋
					R = 0;
				if (L > 0 && R > 0 && h[i] > h[i - 1]) { // 증가하고 감소한 뒤, 새로 증가하게 되었을 때
					result += L * R; // 증가값과 감소값을 곱하여 우뚝선산의 경우의 수 저장
					// 경우의 수 리셋
					L = 0;
					R = 0;
				}
				
				if (h[i] > h[i - 1]) // 증가하고 있다면
					L++;
				if (h[i - 1] > h[i]) // 감소하고 있다면
					R++;

			}

			if (L > 0 && R > 0)
				result += L * R;
			
			System.out.println("#" + tc + " " + result);
		} // end of tc
	} // end of main
} // end of class
