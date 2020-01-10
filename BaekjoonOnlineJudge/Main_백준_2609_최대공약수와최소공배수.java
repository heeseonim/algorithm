import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2609 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int min, max;
		if (A > B) {
			min = B;
			max = A;
		} else {
			min = A;
			max = B;
		}

		while (min != 0) {
			int temp = max % min;
			max = min;
			min = temp;
		}

		System.out.println(max);
		System.out.println((A * B) / max);
	}
}