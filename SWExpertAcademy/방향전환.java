import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 방향전환 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int x = Math.abs(x1 - x2);
			int y = Math.abs(y1 - y2);

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');

			int distance = x + y;
			int max = Math.max(x, y);
			if (distance % 2 == 0)
				sb.append(max * 2);
			else
				sb.append(max * 2 - 1);

			System.out.println(sb.toString());
		}
	}
}
