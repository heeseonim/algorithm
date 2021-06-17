import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M1_추가6_카운팅5단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int arr[][] = new int[][] { { 3, 2, 1, 7 }, { 5, 2, 6, 8 }, { 1, 7, 2, 9 } };
		int target = 6;
		boolean flag = false;

		ex:for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == target) {
					flag = true;
					break ex;
				}
			}
		}

		System.out.println(flag ? "존재" : "안존재");
	}
}
