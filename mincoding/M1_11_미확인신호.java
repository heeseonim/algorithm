import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class M1_11_미확인신호 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		ex : for (int tc = 1; tc <= T; tc++) {
			int M = Integer.parseInt(br.readLine());
			char[] arr = br.readLine().toCharArray();
			int[] check = new int[Character.MAX_VALUE];

			for (int i = 0; i < M; i++) {
				int index = arr[i];
				check[index]++;

				if (check[index] >= 2) {
					sb.append('#').append(tc).append(' ').append("FAIL").append('\n');
					continue ex;
				}
			}

			for (int i = 1; i + M - 1 < arr.length; i++) {
				check[arr[i - 1]]--; // 제거
				check[arr[i + M - 1]]++; // 추가
				
				if (check[arr[i]] >= 2) {
					sb.append('#').append(tc).append(' ').append("FAIL").append('\n');
					continue ex;
				}				
			}
			
			sb.append('#').append(tc).append(' ').append("PASS").append('\n');
		}
		
		System.out.println(sb);
	}
}
