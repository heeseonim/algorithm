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
			
			Arrays.sort(arr);
			boolean check = false;
			for (int i = 1; i < arr.length; i++) {
				check = arr[i].startsWith(arr[i-1]);
				if(check) break;
			}
			System.out.println(check ? "NO" : "YES");
		}
	}
}
