import java.util.Arrays;
import java.util.Scanner;

public class Solution_2063_중간값찾기 {

	public static void main(String[] args) {
		int N;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[] num = new int[200];
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		Arrays.sort(num);
		System.out.println(num[N/2+1]);
	}

}
