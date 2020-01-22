import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9934 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int[] tree = new int[1 << K];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i < (1 << K); i++)
			tree[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= K; i++) {
			for (int j = ((1 << K) + 1) / (1 << i); j < (1 << K); j += (1 << (K - i + 1)))
				System.out.print(tree[j] + " ");

			System.out.println();
		}
	}
}
