import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M1_추가2_카운팅1단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		int vect[] = { 3, 2, 1, 1, 5 };
		int target = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (vect[i] == target)
				cnt++;
		}
		
		System.out.println(cnt + "개");
	}
}
