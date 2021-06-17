import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class M1_10_예식장서빙 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		ex : for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			int[] check = new int[1000000];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 첫 구간 검사
			for (int i = N - R; i < N; i++) {
				int index = arr[i];
				check[index]++;
				if (check[index] > 2) {
					sb.append('#').append(tc).append(' ').append("NO").append('\n');
					continue ex;
				}
			}
			for (int i = 0; i <= R; i++) {
				int index = arr[i];
				check[index]++;
				if (check[index] > 2) {
					sb.append('#').append(tc).append(' ').append("NO").append('\n');
					continue ex;
				}
			}

			// 다음 구간 검사 : 슬라이딩 윈도우
			for (int i = 1; i < N; i++) {
				int remove = i - R - 1;
				if (remove < 0)
					remove += N;
				int add = i + R;
				if (add >= N)
					add -= N;
				
				check[arr[remove]]--; // 제거
				check[arr[add]]++; // 추가
				
				if (check[arr[add]] > 2) {
					sb.append('#').append(tc).append(' ').append("NO").append('\n');
					continue ex;
				}
			}
			sb.append('#').append(tc).append(' ').append("YES").append('\n');			
		}
		
		System.out.println(sb);
	}
}
