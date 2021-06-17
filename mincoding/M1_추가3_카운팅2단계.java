import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M1_추가3_카운팅2단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int arr[][] = new int[][] { { 3, 2, 1, 1 }, { 5, 2, 1, 4 }, { 1, 2, 3, 4 } };
		int target = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == target)
					cnt++;					
			}
		}
		
		System.out.println(cnt + "개");
	}
}
