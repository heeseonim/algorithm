import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class M1_08_카드발급기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		ex : for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] height = new int[N];
			int[] num = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			TreeMap<Integer, Integer> user = new TreeMap<>();
			for (int i = 0; i < N; i++) {
				user.put(num[i], height[i]);
			}
			
			int cur = Integer.MAX_VALUE;
			for(Integer i : user.values()) {
				if (cur < i) {
					sb.append("NO").append('\n');
					continue ex;
				}
				cur = i;
			}
			sb.append("YES").append('\n');
		}
		System.out.println(sb);
	}
}
