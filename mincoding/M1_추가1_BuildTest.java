import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M1_추가1_BuildTest {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1)
			System.out.println("Hi");
		else
			System.out.println("Hello");
	}
}
