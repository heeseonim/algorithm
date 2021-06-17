import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class M1_추가4_카운팅3단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int arr[][] = new int[][] { { 3, 3, 1, 2 }, { 5, 1, 2, 5 }, { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int target[] = { 3, 2, 1, 5 };
		int cnt[] = new int[target.length];
		
		for (int k = 0; k < target.length; k++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (target[k] == arr[i][j])
						cnt[k]++;
				}
			}
		}

		System.out.println(Arrays.toString(cnt));
	}
}
