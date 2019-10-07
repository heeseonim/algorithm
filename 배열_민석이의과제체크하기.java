import java.util.Scanner;

public class Solution_5431_민석이의과제체크하기 {

	public static void main(String[] args) {
		int T, N, K;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();

			boolean[] data = new boolean[N + 1]; // [학생 수+1] 크기의 배열 생성
			for (int i = 1; i <= K; i++) {
				data[sc.nextInt()] = true; // 입력되는 값 위치에 true 저장
			}
			
			System.out.print("#" + tc + " ");
			for (int i = 1; i < N+1; i++) {
				if (data[i] == false)
					System.out.print(i + " ");
			}
			System.out.println();
		}

	} // end of main

} // end of class
