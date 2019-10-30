import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2283 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N; i++) {
			map[i][0] += map[i-1][1] < map[i-1][2] ? map[i-1][1] : map[i-1][2];
			map[i][1] += map[i-1][0] < map[i-1][2] ? map[i-1][0] : map[i-1][2];
			map[i][2] += map[i-1][0] < map[i-1][1] ? map[i-1][0] : map[i-1][1];
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (min > map[N-1][i])
				min = map[N-1][i];
		}
		System.out.println(min);		
	}
}
