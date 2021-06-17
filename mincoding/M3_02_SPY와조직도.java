import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M3_02_SPY와조직도 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int[] id = { 1004, 1680, 9941, 3367, 3261, 2976, 4889, 1234, 6461, 7329, 5518 };

		int[][] map = new int[id.length][id.length];
		map[0][1] = 1;
		map[0][2] = 1;
		map[1][3] = 1;
		map[1][4] = 1;
		map[2][5] = 1;
		map[2][6] = 1;
		map[4][7] = 1;
		map[4][8] = 1;
		map[5][9] = 1;
		map[5][10] = 1;

		int N = Integer.parseInt(br.readLine());
		boolean flag = false;
		for (int i = 0; i < id.length; i++) {
			if (id[i] == N) {
				// 상사
				flag = false;
				int boss = -1;
				for (int j = 0; j < map.length; j++) {
					if (map[j][i] == 1) {
						sb.append(id[j]).append('\n');
						flag = true;
						boss = j;
					}
				}
				if (!flag)
					sb.append("no boss").append('\n');

				// 동료
				if (!flag)
					sb.append("no company").append('\n');
				else {
					flag = false;
					for (int j = 0; i - 1 >= 0 && j < map[i - 1].length; j++) {
						if (j == i)
							continue;

						if (map[boss][j] == 1) {
							sb.append(id[j]).append(' ');
							flag = true;
						}
					}
					if (!flag)
						sb.append("no company").append('\n');
					else
						sb.append('\n');
				}
				// 직속 부하들
				flag = false;
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == 1) {
						sb.append(id[j]).append(' ');
						flag = true;
					}
				}
				if (!flag)
					sb.append("no junior").append('\n');
				else
					sb.append('\n');

				flag = true;
			}
		}
		if (!flag)
			sb.append("no person").append('\n');

		System.out.println(sb);
	}
}
