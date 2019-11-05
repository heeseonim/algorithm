import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_4408_자기방으로돌아가기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] dist = new int[201];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				if (s > e) { // 왼쪽으로 이동이면 시작점과 끝점 변경
					int temp = s;
					s = e;
					e = temp;
				}

				int start;
				int end;
				if (s % 2 == 0) { // 시작점이 짝수이면
					start = s / 2;
				} else {
					start = s / 2 + 1;
				}

				if (e % 2 == 0) {
					end = e / 2;
				} else {
					end = e / 2 + 1;
				}

				for (int j = start; j <= end; j++) {
					dist[j]++;
				}
			}
			Arrays.sort(dist);
			System.out.println("#" + tc + " " + dist[dist.length - 1]);
		}
	}
}
