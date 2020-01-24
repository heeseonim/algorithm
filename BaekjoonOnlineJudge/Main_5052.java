import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_5052 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] arr = new String[N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr); // 정렬
			
			boolean check = false;
			for (int i = 1; i < arr.length; i++) {
				check = arr[i].startsWith(arr[i-1]); // 이전 문장과 비교
				if(check) break; // 이전 문장이 접두사로 시작한다면 break
			}
			System.out.println(check ? "NO" : "YES"); // true 이면 NO 출력
		}
	}
}
