import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_3499_퍼펙트셔플 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] str = new String[N];
			StringBuilder sb = new StringBuilder();
			String[] result = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i ++) {
				str[i] = st.nextToken();
			}
			
			int j = 0;
			for (int i = 0; i < N; i+=2) {
				result[i] = str[j++];
			}

			j = (N%2 == 0) ? N/2 : N/2+1;
			for (int i = 1; i < N; i+=2) {
				result[i] = str[j++];
			}
			
			System.out.print("#" + tc + " ");
			
			for(String s : result) {
				System.out.print(s + " ");
			}
			System.out.println();
			
		}

	}

}
